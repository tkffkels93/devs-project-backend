package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs._core.utils.EncryptUtil;
import com.example.devs._core.utils.JwtUtil;
import com.example.devs._core.utils.LocalDateTimeFormatter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final List<OAuthLoginService> oAuthLoginServices;
    private final AccessTokenStorage accessTokenStorage;

    // 로그인 (관리자)
    @Transactional
    public User adminLogin(UserRequest.AdminLoginDTO adminLoginDTO) {
        User user = userRepository.findByEmailAndUserRole(adminLoginDTO.getEmail(), UserRole.ADMIN)
                .orElseThrow(() -> new Exception404("존재하지 않는 관리자입니다."));

        if (!user.getPassword().equals(adminLoginDTO.getPassword())) {
            throw new Exception400("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    // 회원 정보 리스트 (관리자)
    public UserResponse.UserListDTO getUserList(User sessionAdmin) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        List<UserStatus> validStatuses = Arrays.asList(UserStatus.ACTIVE, UserStatus.BLOCKED);
        List<User> userDTO = userRepository.findByRoleAndStatusIn(UserRole.USER, validStatuses);
        Integer totalUserCount = userDTO.size();
        List<UserResponse.UserList> userList = userDTO.stream().map(UserResponse.UserList::new).toList();

        return new UserResponse.UserListDTO(totalUserCount, userList);
    }

    // 회원 상세 정보 (관리자)
    public UserResponse.DetailDTO getUserDetail(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재하지 않는 회원입니다."));

        return new UserResponse.DetailDTO(user);
    }

    // 회원 삭제 (관리자)
    @Transactional
    public void deleteUser(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재하지 않는 회원입니다."));

        if (user.getStatus() == UserStatus.DELETED) {
            throw new Exception400("이미 삭제된 회원입니다.");
        }

        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    // 회원 차단 (관리자)
    @Transactional
    public void blockUser(User sessionAdmin, Integer userId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception404("존재하지 않는 회원입니다."));

        if (user.getStatus() == UserStatus.BLOCKED) {
            throw new Exception400("이미 차단된 회원입니다.");
        }

        user.setStatus(UserStatus.BLOCKED);
        userRepository.save(user);
    }

    public String login(UserRequest.LoginDTO loginDTO) {
        String msg = "아이디 혹은 비밀번호가 잘못되었습니다.";
        System.out.println(loginDTO.getEmail());
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new Exception404(msg));
        //비밀번호 비교
        if (EncryptUtil.checkPw(loginDTO.getPassword(), user.getPassword())) {//비밀번호일치
            return JwtUtil.userCreate(user); // jwt토큰 생성 후 반환
        } else throw new Exception404(msg); //일치하지않는경우
    }

    public User join(UserRequest.JoinDTO joinDTO) {

        LocalDate bod = null;

        //문자열로된 날짜를 LocalDate 형으로 변환한다.
        if (joinDTO.getYear() != null && joinDTO.getMonth() != null && joinDTO.getDay() != null)
            bod = LocalDate.parse(joinDTO.getYear() + "-" + joinDTO.getMonth() + "-" + joinDTO.getDay());

        User user = User.builder()
                .email(joinDTO.getEmail())
                .password(EncryptUtil.hashPw(joinDTO.getPassword()))
                .nickname(joinDTO.getNickname())
                .username(joinDTO.getUsername())
                .phone(joinDTO.getPhone())
                .birth(bod)
                .image(null)
                .introduce(joinDTO.getIntroduce())
                .position(joinDTO.getPosition())
                .role(UserRole.USER)
                .provider(UserProvider.EMAIL).build();

        user = userRepository.save(user);
        return user;
    }

    // OAuth 로그인
    public String oauthLogin(UserProvider provider, String code) {
        OAuthLoginService<?> loginService = getOAuthProvider(provider);
        UserResponse.OAuthTokenDTO tokenDTO = loginService.getAccessToken(code);

        if (provider == UserProvider.KAKAO) {
            UserResponse.KakaoUserDTO userDTO = (UserResponse.KakaoUserDTO) loginService.getUserInfo(tokenDTO.getAccessToken());
            accessTokenStorage.saveToken(userDTO.getId().toString(), tokenDTO.getAccessToken());
            // 액세스 토큰 출력
            accessTokenStorage.printAllTokens();
            //
            return findOrSaveUser(userDTO, provider);
        } else if (provider == UserProvider.NAVER) {
            UserResponse.NaverUserDTO userDTO = (UserResponse.NaverUserDTO) loginService.getUserInfo(tokenDTO.getAccessToken());
            accessTokenStorage.saveToken(userDTO.getResponse().getId(), tokenDTO.getAccessToken());
            return findOrSaveUser(userDTO, provider);
        } else {
            throw new Exception404("지원하지 않는 OAuth 공급자입니다.");
        }
    }

    // OAuth 공급자에 해당하는 서비스를 반환
    private OAuthLoginService getOAuthProvider(UserProvider provider) {
        return oAuthLoginServices.stream()
                .filter(service -> service.getProvider() == provider)
                .findFirst()
                .orElseThrow(() -> new Exception404("지원하지 않는 OAuth 공급자입니다."));
    }

    // OAuth 사용자 정보를 확인하고, 없으면 새로 생성하여 JWT 토큰 반환
    private <T> String findOrSaveUser(T userDTO, UserProvider provider) {
        String email;

        // 이메일 설정
        if (provider == UserProvider.KAKAO) {
            UserResponse.KakaoUserDTO kakaoUserDTO = (UserResponse.KakaoUserDTO) userDTO;
            email = UserProvider.KAKAO.name() + kakaoUserDTO.getId() + "@kakao.com";
        } else if (provider == UserProvider.NAVER) {
            UserResponse.NaverUserDTO naverUserDTO = (UserResponse.NaverUserDTO) userDTO;
            email = naverUserDTO.getResponse().getEmail();
        } else {
            throw new Exception404("지원하지 않는 OAuth 공급자입니다.");
        }

        Optional<User> loginUser = userRepository.findByEmailAndProvider(email, provider);

        if (loginUser.isPresent()) {
            // 사용자 존재 시 JWT 생성 및 반환
            return JwtUtil.userCreate(loginUser.get());
        } else {
            // 사용자 없을 시 추가 정보 설정 및 새 사용자 생성
            String nickname;
            String username;
            String phone;
            LocalDate birth;
            String profileImage;
            String providerId;

            if (provider == UserProvider.KAKAO) {
                UserResponse.KakaoUserDTO kakaoUser = (UserResponse.KakaoUserDTO) userDTO;
                nickname = kakaoUser.getProperties().getNickname();
                username = kakaoUser.getProperties().getNickname();
                phone = "010-1234-5678"; // 임의로 설정
                birth = LocalDate.now(); // 임의로 설정
                profileImage = kakaoUser.getProperties().getProfileImage();
                providerId = kakaoUser.getId().toString();
            } else {
                UserResponse.NaverUserDTO naverUser = (UserResponse.NaverUserDTO) userDTO;
                nickname = naverUser.getResponse().getNickname();
                username = naverUser.getResponse().getName();
                phone = naverUser.getResponse().getMobile();
                birth = LocalDateTimeFormatter.parseBirth(naverUser.getResponse().getBirthyear(), naverUser.getResponse().getBirthday());
                profileImage = naverUser.getResponse().getProfileImage();
                providerId = naverUser.getResponse().getId();
            }

            User joinUser = User.builder()
                    .email(email)
                    .nickname(nickname)
                    .username(username)
                    .phone(phone)
                    .birth(birth)
                    .image(profileImage)
                    .role(UserRole.USER)
                    .providerId(providerId)
                    .provider(provider)
                    .status(UserStatus.ACTIVE)
                    .build();
            userRepository.save(joinUser);
            return JwtUtil.userCreate(joinUser);
        }
    }

    // OAuth 계정 연결 해제
    public void oauthUnlink(UserProvider userProvider, String jwt) {
        // 해당 OAuth 공급자에 대한 서비스 가져오기
        OAuthLoginService<?> oauthLoginService = getOAuthProvider(userProvider);

        // JWT에서 사용자 ID 추출
        int id = JwtUtil.getUserIdFromJwt(jwt);

        // 사용자 데이터 조회
        Optional<User> user = userRepository.findById(id);

        // Provider ID 및 저장된 Access Token 가져오기
        String providerId = user.get().getProviderId();
        String accessToken = accessTokenStorage.getToken(providerId);

        // OAuth 공급자와의 연결 끊기
        oauthLoginService.unlink(providerId, accessToken);

        // 저장된 Access Token 삭제
        accessTokenStorage.deleteToken(providerId);

        // 사용자 데이터 삭제
        userRepository.deleteById(id);
    }
}

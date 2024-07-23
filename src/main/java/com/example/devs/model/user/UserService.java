package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs._core.utils.EncryptUtil;
import com.example.devs._core.utils.ImageUtil;
import com.example.devs._core.utils.JwtUtil;
import com.example.devs._core.utils.LocalDateTimeFormatter;
import com.example.devs.model.board.Board;
import com.example.devs.model.board.BoardRepository;
import com.example.devs.model.reply.Reply;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final List<OAuthLoginService> oAuthLoginServices;
    private final AccessTokenStorage accessTokenStorage;

    static final String BASIC_IMAGE_PATH = "/src/main/resources/static/images/profile_basic.png";

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
                .image(BASIC_IMAGE_PATH)
                .introduce(joinDTO.getIntroduce())
                .position(joinDTO.getPosition())
                .role(UserRole.USER)
                .provider(UserProvider.EMAIL).build();

        user = userRepository.save(user);
        return user;
    }

    // OAuth 로그인
    public String oauthLogin(UserProvider provider, String accessToken) {
        OAuthLoginService<?> loginService = getOAuthProvider(provider);

        if (provider == UserProvider.KAKAO) {
            UserResponse.KakaoUserDTO userDTO = (UserResponse.KakaoUserDTO) loginService.getUserInfo(accessToken);
            return findOrSaveUser(userDTO, provider);
        } else if (provider == UserProvider.NAVER) {
            UserResponse.NaverUserDTO userDTO = (UserResponse.NaverUserDTO) loginService.getUserInfo(accessToken);
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
                String saveImageName = ImageUtil.downloadImage(kakaoUser.getProperties().getProfileImage());
                nickname = kakaoUser.getProperties().getNickname();
                username = kakaoUser.getProperties().getNickname();
                phone = "010-1234-5678"; // 임의로 설정
                birth = LocalDate.now(); // 임의로 설정
                profileImage = saveImageName;
                providerId = kakaoUser.getId().toString();
            } else {
                UserResponse.NaverUserDTO naverUser = (UserResponse.NaverUserDTO) userDTO;
                String saveImageName = ImageUtil.downloadImage(naverUser.getResponse().getProfileImage());
                nickname = naverUser.getResponse().getNickname();
                username = naverUser.getResponse().getName();
                phone = naverUser.getResponse().getMobile();
                birth = LocalDateTimeFormatter.parseBirth(naverUser.getResponse().getBirthyear(), naverUser.getResponse().getBirthday());
                profileImage = saveImageName;
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
    public void oauthUnlink(UserProvider userProvider, Integer userId) {
        // 해당 OAuth 공급자에 대한 서비스 가져오기
        OAuthLoginService<?> oauthLoginService = getOAuthProvider(userProvider);

        // JWT에서 사용자 ID 추출
        // int id = JwtUtil.getUserIdFromJwt(jwt);

        // 사용자 데이터 조회
        Optional<User> user = userRepository.findById(userId);

        // Provider ID 및 저장된 Access Token 가져오기
        String providerId = user.get().getProviderId();
        String accessToken = accessTokenStorage.getToken(providerId);

        // OAuth 공급자와의 연결 끊기
        oauthLoginService.unlink(providerId, accessToken);

        // 저장된 Access Token 삭제
        accessTokenStorage.deleteToken(providerId);

        // 사용자 데이터 삭제
        userRepository.deleteById(userId);
    }

    // 마이페이지
    public UserResponse.MypageDTO getMyInfo(Integer userId, Pageable pageable, String type) {
        // 사용자 정보 조회
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        // 내가 작성한 게시글 조회
        List<Board> myBoards = userRepository.findMyBoardsById(userId, pageable).getContent();
        // 게시글 목록 변환
        List<UserResponse.MyBoardListDTO> myBoardList = myBoards.stream()
                .map(board -> new UserResponse.MyBoardListDTO(
                        board.getId(),
                        board.getTitle(),
                        LocalDateTimeFormatter.getDuration(board.getCreatedAt()) // 날짜 변환
                ))
                .collect(Collectors.toList());

        // 조회한 정보 DTO에 담기
        UserResponse.MypageDTO mypageDTO = UserResponse.MypageDTO.builder()
                .userId(user.getId())
                .image(user.getImage())
                .nickname(user.getNickname())
                .position(user.getPosition())
                .introduce(user.getIntroduce())
                .myBoardList(myBoardList)
                .build();

        return mypageDTO;
    }

    // 사용자 프로필 조회
    public UserResponse.UserProfileDTO getUserProfile(Integer userId, Pageable pageable) {
        // User 조회
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception404("해당 사용자를 찾을 수 없습니다."));

        // Board 목록 조회
        Page<Board> boards = boardRepository.findByUserId(userId, pageable);

        // 작성한 게시글 수
        Integer boardCount = boardRepository.findBoardCountByUserId(userId);

        // Board 목록 DTO로 변환
        List<UserResponse.UserProfileDTO.UserBoardList> boardList = boards.stream()
                .map(board -> new UserResponse.UserProfileDTO.UserBoardList(
                        board.getId(),
                        board.getTitle(),
                        board.getContent(),
                        LocalDateTimeFormatter.getDuration(board.getCreatedAt()) // 날짜 변환
                ))
                .collect(Collectors.toList());

        // UserProfileDTO로 변환
        UserResponse.UserProfileDTO userProfileDTO = UserResponse.UserProfileDTO.builder()
                .id(user.getId())
                .image(user.getImage())
                .nickname(user.getNickname())
                .position(user.getPosition())
                .introduce(user.getIntroduce())
                .totalBoardCount(boardCount)
                .userBoardList(boardList)
                .build();

        return userProfileDTO;
    }

    // 내가 작성한 게시글 조회(마이페이지)
    public List<UserResponse.MyBoardListDTO> getBoardPage(Integer userId, Pageable pageable) {
        // 내가 작성한 게시글 조회
        List<Board> myBoards = userRepository.findMyBoardsById(userId, pageable).getContent();
        // 게시글 목록 변환
        List<UserResponse.MyBoardListDTO> myBoardListDTO = myBoards.stream()
                .map(board -> new UserResponse.MyBoardListDTO(
                        board.getId(),
                        board.getTitle(),
                        LocalDateTimeFormatter.getDuration(board.getCreatedAt()) // 날짜 변환
                ))
                .collect(Collectors.toList());

        return myBoardListDTO;
    }

    // 내가 작성한 댓글(마이페이지)
    public List<UserResponse.MyReplyListDTO> getMyReplies(Integer userId, Pageable pageable) {
        // 내가 작성한 댓글 조회
        List<Reply> myReplies = userRepository.findMyRepliesById(userId, pageable).getContent();

        // 댓글 목록 변환
            List<UserResponse.MyReplyListDTO> myReplyList = myReplies.stream()
                    .map(reply -> new UserResponse.MyReplyListDTO(
                            reply.getId(),
                            reply.getBoard().getId(),
                            reply.getComment(),
                            reply.getBoard().getTitle(),
                            LocalDateTimeFormatter.getDuration(reply.getCreatedAt()) // 날짜 변환
                    ))
                    .collect(Collectors.toList());

        return myReplyList;
    }

    // 프로필 수정 정보 조회 및 전달
    public UserResponse.UpdateProfileInfoDTO getUpdateProfileInfo(Integer id) {
        // 사용자 정보 조회
        Optional<User> user = userRepository.findById(id);

        // DTO 생성 및 값 설정
        UserResponse.UpdateProfileInfoDTO updateProfileInfoDTO = UserResponse.UpdateProfileInfoDTO.builder()
               .nickname(user.get().getNickname())
               .position(user.get().getPosition())
               .introduce(user.get().getIntroduce())
               .profileImg(user.get().getImage())
               .build();

        return updateProfileInfoDTO;
    }

    // 프로필 업데이트
    public UserResponse.UpdateProfileInfoDTO updateProfile(Integer id, UserRequest.UpdateProfileDTO resquestDTO) throws IOException {
        // 사진 인코딩
        List<ImageUtil.FileUploadResult> fileUploadResults = ImageUtil.uploadBase64Images(resquestDTO.getProfileImg());
        String profileImg = fileUploadResults.get(0).getFilePath() + fileUploadResults.get(0).getFileName();

        // 업데이트 정보 DB에 전달
        Integer result = userRepository.updateProfileById(id,
                                                          resquestDTO.getNickname(),
                                                          resquestDTO.getPosition(),
                                                          resquestDTO.getIntroduce(),
                                                          profileImg);

        // 업데이트 실패
        if (result != 1) { throw new Exception400("업데이트 실패."); }

        // 결과 반환
        return UserResponse.UpdateProfileInfoDTO.builder()
                .nickname(resquestDTO.getNickname())
                .position(resquestDTO.getPosition())
                .introduce(resquestDTO.getIntroduce())
                .profileImg(profileImg)
                .build();
    }

}

package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs._core.utils.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

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

    // OAuth2.0 인증을 위한 상수 정의
    final String GRANT_TYPE = "authorization_code"; // OAuth2.0 인증 타입
    final String KAKAO_CLIENT_ID = "3e811404984aeead4e15eeeb1393907f"; // 카카오 클라이언트 ID
    final String KAKAO_REDIRECT_URI = "http://localhost:8080/api/users/oauth/kakao"; // 카카오 리디렉션 URI
    final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token"; // 카카오 토큰 요청 URL
    final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 카카오 사용자 정보 요청 URL
    final String NAVER_CLIENT_ID = "nfdBh7_HSSjdAvaBPLWs"; // 네이버 클라이언트 ID
    final String NAVER_CLIENT_SECRET = "zgUl5Ga7qs"; // 네이버 시크릿 키
    final String NAVER_REDIRECT_URI = "http://localhost:8080/api/users/oauth/never"; // 네이버 리디렉션 URI
    final String NAVER_TOKEN_REQUEST_URL = "https://nid.naver.com/oauth2.0/token"; // 네이버 토큰 요청 URL
    final String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me"; // 네이버 사용자 정보 요청 URL

    public String kakaoLogin(String code) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 1-1. http header 설정
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.add(HttpHeadersConstants.CONTENT_TYPE, HttpHeadersConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);

        // 1-2. http body 설정
        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", GRANT_TYPE);
        tokenRequestBody.add("client_id", KAKAO_CLIENT_ID);
        tokenRequestBody.add("redirect_uri", KAKAO_REDIRECT_URI);
        tokenRequestBody.add("code", code);

        // 1-3. body + header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        // 1-4. api 요청하기 (토큰 받기)
        ResponseEntity<UserResponse.KakaoTokenDTO> token = restTemplate.exchange(
                KAKAO_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                tokenRequest,
                UserResponse.KakaoTokenDTO.class);

        // 2. 토큰으로 사용자 정보 받기
        HttpHeaders userInfoRequestHeaders = new HttpHeaders();
        userInfoRequestHeaders.add(HttpHeadersConstants.CONTENT_TYPE, HttpHeadersConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);
        userInfoRequestHeaders.add(JwtVO.HEADER, JwtVO.PREFIX + token.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(userInfoRequestHeaders);

        ResponseEntity<UserResponse.KakaoUserDTO> userInfoResponse = restTemplate.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                userInfoRequest,
                UserResponse.KakaoUserDTO.class);

        // 3. 해당정보로 DB조회 (회원인 경우, 회원이 아닌 경우)
        // FIXME: 메일이 안받아져서 KAKAO + PK + 하드코딩된 이메일 생성
        String email = UserProvider.KAKAO.name() + userInfoResponse.getBody().getId() + "@kakao.com";
        // FIXME: 카카오 정보가 제한적이라 유니크한 값을 어떤 것으로 지정해야 할지 모르겠음
        User loginUser = userRepository.findByEmailV2(email);

        // 4. 회원인 경우 회원이 아닌 경우 판별
        if (loginUser != null) {
            // 회원인 경우
            System.out.println("########## 회원인 경우 ##########");
            return JwtUtil.userCreate(loginUser);
        } else {
            // 회원이 아닌 경우: 가입 후 로그인
            System.out.println("########## 회원이 아닌 경우 ##########");
            User user = User.builder()
                    // FIXME: 메일이 안받아져요.
                    .email(email)
                    .nickname(userInfoResponse.getBody().getProperties().getNickname())
                    // FIXME: 이름이 안받아져요.
                    .username("김덕배")
                    // FIXME: 번호가 안받아져요.
                    .phone("010-1234-5678")
                    // FIXME: 생일이 안받아져요.
                    .birth(LocalDate.now())
                    .image(userInfoResponse.getBody().getProperties().getProfileImage())
                    .role(UserRole.USER)
                    // .providerId("프로바이더 고유 번호가 뭐죠?")
                    .provider(UserProvider.KAKAO)
                    .status(UserStatus.ACTIVE)
                    .build();
            User joinUser = userRepository.save(user);
            return JwtUtil.userCreate(joinUser);
        }
    }

    public String naverLogin(String code) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 1-1. http header 설정
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.add(HttpHeadersConstants.CONTENT_TYPE, HttpHeadersConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);

        // 1-2. http body 설정
        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", GRANT_TYPE);
        tokenRequestBody.add("client_id", NAVER_CLIENT_ID);
        tokenRequestBody.add("client_secret", NAVER_CLIENT_SECRET);
        tokenRequestBody.add("redirect_uri", NAVER_REDIRECT_URI);
        tokenRequestBody.add("code", code);

        // 1-3. body + header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        // 1-4. api 요청하기 (토큰 받기)
        ResponseEntity<UserResponse.NaverTokenDTO> token = restTemplate.exchange(
                NAVER_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                tokenRequest,
                UserResponse.NaverTokenDTO.class);

        // 2. 토큰으로 사용자 정보 받기
        HttpHeaders userInfoRequestHeaders = new HttpHeaders();
        userInfoRequestHeaders.add(HttpHeadersConstants.CONTENT_TYPE, HttpHeadersConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);
        userInfoRequestHeaders.add(JwtVO.HEADER, JwtVO.PREFIX + token.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(userInfoRequestHeaders);

        ResponseEntity<UserResponse.NaverUserDTO> userInfoResponse = restTemplate.exchange(
                NAVER_USER_INFO_URL,
                HttpMethod.GET,
                userInfoRequest,
                UserResponse.NaverUserDTO.class);

        // 3. 해당정보로 DB조회 (회원인 경우, 회원이 아닌 경우)
        User loginUser = userRepository.findByEmailV2(userInfoResponse.getBody().getResponse().getEmail());

        // 4. 회원인 경우 회원이 아닌 경우 판별
        if (loginUser != null) {
            // 회원인 경우
            System.out.println("########## 회원인 경우 ##########");
            return JwtUtil.userCreate(loginUser);
        } else {
            // 회원이 아닌 경우: 가입 후 로그인
            System.out.println("########## 회원이 아닌 경우 ##########");
            User user = User.builder()
                    .email(userInfoResponse.getBody().getResponse().getEmail())
                    .nickname(userInfoResponse.getBody().getResponse().getNickname())
                    .username(userInfoResponse.getBody().getResponse().getName())
                    .phone(userInfoResponse.getBody().getResponse().getMobile())
                    .birth(LocalDateTimeFormatter.parseBirth(userInfoResponse.getBody().getResponse().getBirthyear(), userInfoResponse.getBody().getResponse().getBirthday()))
                    .image(userInfoResponse.getBody().getResponse().getProfileImage())
                    .role(UserRole.USER)
                    // .providerId("프로바이더 고유 번호가 뭐죠?")
                    .provider(UserProvider.NAVER)
                    .status(UserStatus.ACTIVE)
                    .build();
            User joinUser = userRepository.save(user);
            return JwtUtil.userCreate(joinUser);
        }
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
                .image(null)
                .introduce(joinDTO.getIntroduce())
                .position(joinDTO.getPosition())
                .role(UserRole.USER)
                .provider(UserProvider.EMAIL).build();

        user = userRepository.save(user);
        return user;
    }
}

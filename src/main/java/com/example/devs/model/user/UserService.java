package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.utils.JwtUtil;
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
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // OAuth2.0 인증을 위한 상수 정의
    final String GRANT_TYPE = "authorization_code"; // OAuth2.0 인증 타입
    final String KAKAO_CLIENT_ID = "3e811404984aeead4e15eeeb1393907f"; // 카카오 클라이언트 ID
    final String KAKAO_REDIRECT_URI = "http://localhost:8080/api/users/oauth/kakao"; // 카카오 리디렉션 URI
    final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token"; // 카카오 토큰 요청 URL
    final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 카카오 사용자 정보 요청 URL

    public String kakaoLogin(String code){
        // 1.1 RestTemplate 설정
        RestTemplate rt = new RestTemplate();

        // 1.2 http header 설정
        HttpHeaders codeHeaders = new HttpHeaders();
        codeHeaders.add("Content-Type", "application/x-www-form-urlencoded");

        // 1.3 http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", KAKAO_CLIENT_ID);
        body.add("redirect_uri", KAKAO_REDIRECT_URI);
        body.add("code", code);

        // 1.4 body+header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> codeRequest = new HttpEntity<>(body, codeHeaders);

        // 1.5 api 요청하기 (토큰 받기)
        ResponseEntity<UserResponse.TokenDTO> response = rt.exchange(
                KAKAO_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                codeRequest,
                UserResponse.TokenDTO.class);

        // 2. 토큰으로 사용자 정보 받기 (PK, Email)
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        tokenHeaders.add("Authorization", "Bearer "+response.getBody().getAccessToken());

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenHeaders);

        ResponseEntity<UserResponse.KakaoUserDTO> tokenResponse = rt.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                tokenRequest,
                UserResponse.KakaoUserDTO.class);

        // 3. 해당정보로 DB조회 (있을수, 없을수)
        String nickname = UserProvider.KAKAO.name() + tokenResponse.getBody().getId();
        User loginUser = userRepository.findByNickname(nickname);

        // 4. 있으면? - 조회된 유저정보 리턴
        if(loginUser != null){
            // 이미 가입된 회원
            return JwtUtil.userCreate(loginUser);
        }else{
            // 가입 안 한 회원: 가입 후 로그인
            User user = User.builder()
                    .email(tokenResponse.getBody().getProperties().getNickname()+"@nate.com")
                    .password(UUID.randomUUID().toString())
                    .nickname(nickname)
                    .username("이름이 안받아져요")
                    .phone("번호가 안받아져요")
                    .birth(LocalDate.now()) // 생일이 안받아져요.
                    .image(tokenResponse.getBody().getProperties().getProfileImage())
                    .role(UserRole.USER)
                    .providerId("프로바이더 고유 번호가 뭐죠?")
                    .provider(UserProvider.KAKAO)
                    .status(UserStatus.ACTIVE)
                    .build();
            User joinUser = userRepository.save(user);
            return JwtUtil.userCreate(joinUser);
        }
    }
}

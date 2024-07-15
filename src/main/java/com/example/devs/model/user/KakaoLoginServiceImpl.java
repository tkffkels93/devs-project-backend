package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.utils.JwtVO;
import com.example.devs._core.utils.OauthConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoLoginServiceImpl implements OAuthLoginService {

    private final UserRepository userRepository;

    // 카카오 관련 상수
    public static final String KAKAO_CLIENT_ID = "3e811404984aeead4e15eeeb1393907f"; // 카카오 클라이언트 ID
    public static final String KAKAO_REDIRECT_URI = "http://localhost:8080/api/users/oauth/kakao"; // 카카오 리디렉션 URI
    public static final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token"; // 카카오 토큰 요청 URL
    public static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 카카오 사용자 정보 요청 URL

    @Override
    public UserProvider getProvider() {
        return UserProvider.KAKAO;
    }

    @Override
    public UserResponse.KakaoTokenDTO getAccessToken(String authorizationCode) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 2. http header 설정
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.add(OauthConstants.CONTENT_TYPE, OauthConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);

        // 3. http body 설정
        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", OauthConstants.GRANT_TYPE);
        tokenRequestBody.add("client_id", KAKAO_CLIENT_ID);
        tokenRequestBody.add("redirect_uri", KAKAO_REDIRECT_URI);
        tokenRequestBody.add("code", authorizationCode);

        // 4. body + header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        // 5. api 요청하기 (토큰 받기)
        ResponseEntity<UserResponse.KakaoTokenDTO> accessToken = restTemplate.exchange(
                KAKAO_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                tokenRequest,
                UserResponse.KakaoTokenDTO.class);

        return accessToken.getBody();
    }

    @Override
    public UserResponse.KakaoUserDTO getUserInfo(String accessToken) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 2. http header 설정
        HttpHeaders userInfoRequestHeaders = new HttpHeaders();
        userInfoRequestHeaders.add(OauthConstants.CONTENT_TYPE, OauthConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);
        userInfoRequestHeaders.add(JwtVO.HEADER, JwtVO.PREFIX + accessToken);

        // 3. body 없이 http header만 설정
        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(userInfoRequestHeaders);

        // 4. api 요청하기 (사용자 정보 받기)
        ResponseEntity<UserResponse.KakaoUserDTO> userInfoResponse = restTemplate.exchange(
                KAKAO_USER_INFO_URL,
                HttpMethod.GET,
                userInfoRequest,
                UserResponse.KakaoUserDTO.class);

        return userInfoResponse.getBody();
    }
}

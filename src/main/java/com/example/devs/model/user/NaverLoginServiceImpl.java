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
public class NaverLoginServiceImpl implements OAuthLoginService {

    // 네이버 관련 상수
    public static final String NAVER_CLIENT_ID = "nfdBh7_HSSjdAvaBPLWs"; // 네이버 클라이언트 ID
    public static final String NAVER_CLIENT_SECRET = "zgUl5Ga7qs"; // 네이버 시크릿 키
    public static final String NAVER_REDIRECT_URI = "http://localhost:8080/api/users/oauth/never"; // 네이버 리디렉션 URI
    public static final String NAVER_TOKEN_REQUEST_URL = "https://nid.naver.com/oauth2.0/token"; // 네이버 토큰 요청 URL
    public static final String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me"; // 네이버 사용자 정보 요청 URL

    @Override
    public UserProvider getProvider() {
        return UserProvider.NAVER;
    }

    @Override
    public UserResponse.OAuthTokenDTO getAccessToken(String authorizationCode) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 2. http header 설정
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.add(OauthConstants.CONTENT_TYPE, OauthConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);

        // 3. http body 설정
        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", OauthConstants.GRANT_TYPE);
        tokenRequestBody.add("client_id", NAVER_CLIENT_ID);
        tokenRequestBody.add("client_secret", NAVER_CLIENT_SECRET);
        tokenRequestBody.add("redirect_uri", NAVER_REDIRECT_URI);
        tokenRequestBody.add("code", authorizationCode);

        // 4. body + header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        // 5. api 요청하기 (토큰 받기)
        ResponseEntity<UserResponse.OAuthTokenDTO> accessToken = restTemplate.exchange(
                NAVER_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                tokenRequest,
                UserResponse.OAuthTokenDTO.class);

        return accessToken.getBody();
    }

    @Override
    public UserResponse.NaverUserDTO getUserInfo(String accessToken) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 2. http header 설정
        HttpHeaders userInfoRequestHeaders = new HttpHeaders();
        userInfoRequestHeaders.add(OauthConstants.CONTENT_TYPE, OauthConstants.CONTENT_TYPE_FORM_URLENCODED_UTF8);
        userInfoRequestHeaders.add(JwtVO.HEADER, JwtVO.PREFIX + accessToken);

        // 3. body 없이 http header만 설정
        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(userInfoRequestHeaders);

        // 4. api 요청하기 (사용자 정보 받기)
        ResponseEntity<UserResponse.NaverUserDTO> userInfoResponse = restTemplate.exchange(
                NAVER_USER_INFO_URL,
                HttpMethod.GET,
                userInfoRequest,
                UserResponse.NaverUserDTO.class);

        return userInfoResponse.getBody();
    }

    @Override
    public UserResponse.NaverUnlinkDTO unlink(String providerId, String accessToken) {
        // 1. RestTemplate 설정
        RestTemplate restTemplate = new RestTemplate();

        // 2. http header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtVO.HEADER, JwtVO.PREFIX + accessToken);

        // 3. http body 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", NAVER_CLIENT_ID);
        body.add("client_secret", NAVER_CLIENT_SECRET);
        body.add("access_token", accessToken);
        body.add("grant_type", "delete");

        // 4. body + header 객체 만들기
        HttpEntity<MultiValueMap<String, String>> unlinkRequest = new HttpEntity<>(body, headers);

        // 5. api 요청하기 (연결 끊기)
        ResponseEntity<UserResponse.NaverUnlinkDTO> unlinkResponse = restTemplate.exchange(
                NAVER_TOKEN_REQUEST_URL,
                HttpMethod.POST,
                unlinkRequest,
                UserResponse.NaverUnlinkDTO.class);

        return unlinkResponse.getBody();
    }
}

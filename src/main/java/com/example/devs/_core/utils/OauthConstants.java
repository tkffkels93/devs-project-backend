package com.example.devs._core.utils;

public class OauthConstants {
    // OAuth2.0 인증을 위한 상수 정의
    public static final String GRANT_TYPE = "authorization_code"; // OAuth2.0 인증 타입

    // 카카오 관련 상수
    public static final String KAKAO_CLIENT_ID = "3e811404984aeead4e15eeeb1393907f"; // 카카오 클라이언트 ID
    public static final String KAKAO_REDIRECT_URI = "http://localhost:8080/api/users/oauth/kakao"; // 카카오 리디렉션 URI
    public static final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token"; // 카카오 토큰 요청 URL
    public static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me"; // 카카오 사용자 정보 요청 URL

    // 네이버 관련 상수
    public static final String NAVER_CLIENT_ID = "nfdBh7_HSSjdAvaBPLWs"; // 네이버 클라이언트 ID
    public static final String NAVER_CLIENT_SECRET = "zgUl5Ga7qs"; // 네이버 시크릿 키
    public static final String NAVER_REDIRECT_URI = "http://localhost:8080/api/users/oauth/never"; // 네이버 리디렉션 URI
    public static final String NAVER_TOKEN_REQUEST_URL = "https://nid.naver.com/oauth2.0/token"; // 네이버 토큰 요청 URL
    public static final String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me"; // 네이버 사용자 정보 요청 URL
}

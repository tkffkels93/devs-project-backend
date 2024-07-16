package com.example.devs._core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.devs.model.user.SessionAdmin;
import com.example.devs.model.user.SessionUser;
import com.example.devs.model.user.User;

import java.util.Date;

public class JwtUtil {

    // user 토큰 생성
    public static String userCreate(User user) {
        // JWT 토큰 생성
        String jwt = JWT.create()
                // 토큰의 주제(subject) 설정
                .withSubject("community")
                // 토큰 만료 시간 설정 (현재 시간 + 24시간)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L)) // 1년간 지속
                // 토큰에 사용자의 정보(Claim) 설정
                .withClaim("id", user.getId())
                .withClaim("email", user.getEmail())
                .withClaim("role", "USER")
                // 토큰 서명(Sign) 설정 (암호화 알고리즘은 HMAC512 사용)
                .sign(Algorithm.HMAC512("devs")); // 대칭키 사용 나중에 devs 이라 적은 자리에 환경 변수를 넣는다 OS 의 값을 땡겨와야한다!
        return jwt; // 생성된 JWT 토큰 반환
    }

    // user 토큰 검증
    public static SessionUser userVerify(String jwt) {
        // JWT 토큰 복호화 및 검증
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("devs")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String email = decodedJWT.getClaim("email").asString();

        // 임시 세션을 이용
        return SessionUser.builder()
                .id(id)
                .email(email)
                .build();
    }

    // admin 토큰 생성
    public static String adminCreate(User user) {
        String jwt = JWT.create()
                .withSubject("community")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60L * 60L * 24L * 365L)) // 1년간 지속
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", "ADMIN")
                .sign(Algorithm.HMAC512("devs")); // 대칭키 사용 나중에 devs 이라 적은 자리에 환경 변수를 넣는다 OS 의 값을 땡겨와야한다!
        return jwt;
    }

    // admin 토큰 검증
    public static SessionAdmin adminVerify(String jwt) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("devs")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String name = decodedJWT.getClaim("username").asString();

        // 임시 세션을 이용
        return SessionAdmin.builder()
                .id(id)
                .username(name)
                .build();
    }

    // JWT 토큰에서 사용자 ID 추출
    public static int getUserIdFromJwt(String jwt) {
        // JWT 토큰을 해독하고 검증
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("devs"))
                .build()
                .verify(jwt.replace("Bearer ", ""));

        // 해독된 JWT에서 'id' 클레임을 추출
        return decodedJWT.getClaim("id").asInt();
    }
}
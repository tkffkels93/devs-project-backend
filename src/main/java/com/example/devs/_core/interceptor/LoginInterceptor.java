package com.example.devs._core.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception500;
import com.example.devs._core.utils.JwtUtil;
import com.example.devs.model.user.SessionAdmin;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // jwt 토큰
        String jwt = request.getHeader("Authorization");
        System.out.println("============preHandle");

        // interceptor 에서 안하면 throw 날리기가 힘들다!
        // 토큰 전달 검사
        if (jwt == null) {
            throw new Exception401("jwt 토큰을 전달해주세요");
        }

        // Bearer jwt토큰 -> 띄워쓰기를 유의해서 보자! 프로토콜 이다!
        jwt = jwt.replace("Bearer ", "");

        System.out.println("==========JWT==========");
        System.out.println(jwt);
        System.out.println("==========JWT==========");
        // 검증
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("devs")).build().verify(jwt);
            String role = decodedJWT.getClaim("role").asString();

            // claim 을 이용하여 role 의 값에 따라 company 와 user, admin 구분
            if ("ADMIN".equals(role)) {
                System.out.println("===============ADMIN===============");
                SessionAdmin sessionAdmin = JwtUtil.adminVerify(jwt);
                HttpSession session = request.getSession();
                session.setAttribute("sessionAdmin", sessionAdmin);
            } else {
                System.out.println("===============USER===============");
                SessionUser sessionUser = JwtUtil.userVerify(jwt);
                // 임시 세선 (jsessionId 는 필요 없다!)
                HttpSession session = request.getSession();
                session.setAttribute("sessionUser", sessionUser);
            }

            return true;
        } catch (TokenExpiredException e) {
            throw new Exception401("토큰 만료시간이 지났어요. 다시 로그인하세요");
        } catch (JWTDecodeException e) {
            throw new Exception401("토큰이 유효하지 않습니다");
        } catch (Exception e) {
            e.printStackTrace(); // 개발 진행 시 TEST 보기
            throw new Exception500(e.getMessage()); // 알 수 없는 오류 이니깐 500으로 다 던져 준다.
        }
    }
}

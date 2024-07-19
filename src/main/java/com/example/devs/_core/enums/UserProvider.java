package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserProvider {
    EMAIL("기본"), // 기본
    KAKAO("카카오"), // 카카오
    NAVER("네이버"), // 네이버
    GOOGLE("구글"); // 구글

    private final String korean;
}

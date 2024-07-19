package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    ADMIN("관리자"), // 관리자
    USER("회원"); // 회원

    private final String korean;
}

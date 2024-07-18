package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserStatus {
    ACTIVE("활동"), // 활동
    BLOCKED("차단"), // 차단
    DELETED("삭제"); // 삭제

    private final String korean;
}

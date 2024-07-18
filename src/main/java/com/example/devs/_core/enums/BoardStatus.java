package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BoardStatus {
    PUBLISHED("게시"), // 게시글 등록
    HIDE("숨김"), // 게시글 숨김
    DELETED("삭제"); // 게시글 삭제

    private final String korean;
}

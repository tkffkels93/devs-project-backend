package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReplyStatus {
    PUBLISHED("게시"), // 댓글 등록
    HIDE("숨김"), // 댓글 숨김
    DELETED("삭제"); // 댓글 삭제

    private final String korean;
}

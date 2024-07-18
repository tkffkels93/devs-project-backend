package com.example.devs._core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BoardRole {
    Board("게시판"),
    Inquiry("질문"),
    Answer("답변");

    private final String korean;
}

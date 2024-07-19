package com.example.devs.model.reply;

import lombok.Data;

public class ReplyRequest {

    @Data
    public static class ReplySaveDTO {
        private String comment;
    }
}

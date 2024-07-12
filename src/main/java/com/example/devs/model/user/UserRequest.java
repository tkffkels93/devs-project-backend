package com.example.devs.model.user;

import lombok.Data;

public class UserRequest {

    // 관리자 로그인 DTO
    @Data
    public static class AdminLoginDTO {
        private String email;
        private String password;

        public AdminLoginDTO(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}

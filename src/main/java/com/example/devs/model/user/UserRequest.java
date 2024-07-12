package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import lombok.Data;

import java.time.LocalDate;

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

    @Data
    public static class LoginDTO {
        private String email;
        private String password;
    }

    @Data
    public static class JoinDTO {
        private String email;
        private String password;
        private String nickname;
        private String username;
        private String phone;
        private String year;
        private String month;
        private String day;
        private String image;
        private LocalDate birthdate;
        private UserRole role = UserRole.USER;
        private UserProvider provider = UserProvider.EMAIL;
        private String providerId = null;
    }
}

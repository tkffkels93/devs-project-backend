package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserRequest {
    @Data
    public static class LoginDTO{
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

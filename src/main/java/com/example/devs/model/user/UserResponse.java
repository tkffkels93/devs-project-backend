package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserResponse {

    // 로그인 DTO (관리자)
    @Data
    public static class AdminLoginDTO {
        private Integer id;
        private String email;
        private String nickname;
        private String username;
        private UserProvider provider;

        public AdminLoginDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.username = user.getUsername();
            this.provider = user.getProvider();
        }
    }

    // 회원 정보 리스트 DTO (관리자)
    @Data
    public static class UserListDTO {
        private Integer totalUserCount;
        private List<UserList> userList;

        public UserListDTO(Integer totalUserCount, List<UserList> userList) {
            this.totalUserCount = totalUserCount;
            this.userList = userList;
        }
    }

    @Data
    public static class UserList {
        private Integer id;
        private String email;
        private String username;
        private String nickname;
        private UserProvider provider;
        private UserStatus status;

        public UserList(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.provider = user.getProvider();
            this.status = user.getStatus();
        }
    }

    // 회원 상세 정보 DTO (관리자)
    @Data
    public static class DetailDTO {
        private Integer id;
        private String email;
        private String username;
        private String nickname;
        private String phone;
        private LocalDate birth;
        private String image;
        private UserProvider provider;
        private UserStatus status;
        private String createdAt;
        private String updatedAt;

        public DetailDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.phone = user.getPhone();
            this.birth = user.getBirth();
            this.image = user.getImage();
            this.provider = user.getProvider();
            this.status = user.getStatus();
            this.createdAt = LocalDateTimeFormatter.convert(user.getCreatedAt());
            this.updatedAt = Optional.ofNullable(user.getUpdatedAt())
                    .map(LocalDateTimeFormatter::convert)
                    .orElse("N/A");
        }
    }
}

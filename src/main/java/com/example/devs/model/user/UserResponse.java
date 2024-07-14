package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserStatus;
import com.example.devs._core.utils.LocalDateTimeFormatter;
import com.example.devs._core.utils.ScopeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.sql.Timestamp;
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

    // 공통 OAuth 토큰 정보 DTO
    @Data
    public static class OAuthTokenDTO {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("expires_in")
        private Integer expiresIn;
    }

    // 카카오 전용 추가 토큰 정보 DTO
    @Data
    public static class KakaoTokenDTOV2 extends OAuthTokenDTO {
        @JsonDeserialize(using = ScopeConverter.class)
        private List<String> scope;
        @JsonProperty("refresh_token_expires_in")
        private Integer refreshTokenExpiresIn;
    }

    // 카카오 유저 DTO
    @Data
    public static class KakaoUserDTO {
        private Long id;
        @JsonProperty("connected_at")
        private Timestamp connectedAt;
        private Properties properties;

        @Data
        class Properties {
            private String nickname;
            @JsonProperty("profile_image")
            private String profileImage;
        }
    }

    // 네이버 유저 DTO
    @Data
    public static class NaverUserDTO {
        private Response response;

        @Data
        class Response {
            private String id;
            private String nickname;
            @JsonProperty("profile_image")
            private String profileImage;
            private String email;
            private String mobile;
            @JsonProperty("mobile_e164")
            private String mobileE164; // 국제 전화
            private String name;
            private String birthday;
            private String birthyear;
        }
    }
}

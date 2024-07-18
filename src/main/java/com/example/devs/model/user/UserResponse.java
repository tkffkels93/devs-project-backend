package com.example.devs.model.user;

import com.example.devs._core.utils.LocalDateTimeFormatter;
import com.example.devs._core.utils.ScopeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
        private String provider;

        public AdminLoginDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.username = user.getUsername();
            this.provider = user.getProvider().getKorean();
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
        private String provider;
        private String status;

        public UserList(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.provider = user.getProvider().getKorean();
            this.status = user.getStatus().getKorean();
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
        private String position;
        private String introduce;
        private LocalDate birth;
        private String image;
        private String provider;
        private String status;
        private String createdAt;
        private String updatedAt;

        public DetailDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.phone = user.getPhone();
            this.position = user.getPosition();
            this.introduce = user.getIntroduce();
            this.birth = user.getBirth();
            this.image = user.getImage();
            this.provider = user.getProvider().getKorean();
            this.status = user.getStatus().getKorean();
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
    public static class KakaoTokenDTO extends OAuthTokenDTO {
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

    // 카카오 연결 해제 DTO
    @Data
    public static class KakaoUnlinkDTO {
        private Long id;
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

    // 카카오 연결 해제 DTO
    @Data
    public static class NaverUnlinkDTO {
        @JsonProperty("access_token")
        private String accessToken;
        private String result;
    }

    // 마이페이지
    @Builder
    @Data
    public static class MypageDTO {
        private Integer id;                    // PK
        private String image;                  // 프로필 사진
        private String nickname;               // 닉네임
        private String position;               // 직함
        private String introduce;              // 자기소개
        private List<MyBoardList> myBoardList; // 내가 작성한 글
        private List<MyReplyList> myReplyList; // 내가 작성한 댓글

        // 내가 작성한 글
        @AllArgsConstructor
        @Builder
        @Data
        public static class MyBoardList {
            private Integer id;       // PK
            private String title;     // 제목
            private String updatedAt; // 작성일
        }

        // 내가 작성한 댓글
        @Builder
        @Data
        public static class MyReplyList {
            private Integer id;        // PK
            private String comment;    // 댓글 내용
            private String boardTitle; // 댓글이 작성된 글 제목
            private String updatedAt;  // 작성일
        }
    }

}

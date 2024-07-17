package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionUser {
    private Integer id; // 유저 번호
    private String email; // 이메일
    private String nickname; // 닉네임
    private String username; // 이름
    private String phone; // 연락처
    private LocalDate birth; // 생년월일
    private String image; // 프로필 사진
    private UserProvider provider; // 프로바이더
    private UserStatus status; // 상태

    @Builder
    public SessionUser(Integer id, String email, String nickname, String username, String phone, LocalDate birth, String image, UserProvider provider, UserStatus status) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.username = username;
        this.phone = phone;
        this.birth = birth;
        this.image = image;
        this.provider = provider;
        this.status = status;
    }

    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.birth = user.getBirth();
        this.image = user.getImage();
        this.provider = user.getProvider();
        this.status = user.getStatus();
    }
}

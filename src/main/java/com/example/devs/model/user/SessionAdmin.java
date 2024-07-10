package com.example.devs.model.user;

import com.example.devs.model.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionAdmin extends BaseEntity {
    private Integer id; // 유저 번호
    private String email; // 이메일
    private String nickname; // 닉네임
    private String username; // 이름
    private String phone; // 연락처
    private LocalDate birth; // 생년월일
    private String image; // 프로필 사진

    @Builder
    public SessionAdmin(Integer id, String email, String nickname, String username, String phone, LocalDate birth, String image) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.username = username;
        this.phone = phone;
        this.birth = birth;
        this.image = image;
    }

    public SessionAdmin(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.birth = user.getBirth();
        this.image = user.getImage();
    }
}

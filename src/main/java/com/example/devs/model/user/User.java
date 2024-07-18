package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.enums.UserRole;
import com.example.devs._core.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "user_tb")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 유저 번호

    private String email; // 이메일

    private String password; // 비밀번호

    private String nickname; // 닉네임

    private String username; // 이름

    private String phone; // 연락처

    private String position; // 직함

    private LocalDate birth; // 생년월일

    private String image; // 프로필 사진

    private String introduce; //자기소개

    @Enumerated(EnumType.STRING)
    private UserRole role; // 권한

    private String providerId; // 프로바이더 고유번호

    @Enumerated(EnumType.STRING)
    private UserProvider provider; // 프로바이더

    @Enumerated(EnumType.STRING)
    private UserStatus status; // 상태

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정일

    @Builder
    public User(Integer id, String email, String password, String nickname, String username, String phone, String position, LocalDate birth, String image, String introduce, UserRole role, String providerId, UserProvider provider, UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.username = username;
        this.phone = phone;
        this.position = position;
        this.birth = birth;
        this.image = image;
        this.introduce = introduce;
        this.role = role;
        this.providerId = providerId;
        this.provider = provider;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

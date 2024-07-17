package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import com.example.devs.model.reply.Reply;
import com.example.devs.model.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Board 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // User

    @Enumerated(EnumType.STRING)
    private BoardRole BoardRole; // Board 구분

    private String title; // 제목

    @Column(length = 1000) // 여기서 content 필드의 길이를 1000으로 설정합니다.
    private String content; // 내용

    private Integer hit; // 조회수

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies; // 댓글 리스트

    @Enumerated(EnumType.STRING)
    private BoardStatus status; // 게시글 상태

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정일

    @Builder
    public Board(Integer id, User user, com.example.devs._core.enums.BoardRole boardRole, String title, String content, Integer hit, BoardStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        BoardRole = boardRole;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Builder
    public Board(Integer id, User user, com.example.devs._core.enums.BoardRole boardRole, String title, String content, Integer hit, List<Reply> replies, BoardStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        BoardRole = boardRole;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.replies = replies;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

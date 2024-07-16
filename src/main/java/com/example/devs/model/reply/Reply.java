package com.example.devs.model.reply;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.ReplyStatus;
import com.example.devs.model.board.Board;
import com.example.devs.model.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "reply_tb")
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 댓글 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // User

    @Enumerated(EnumType.STRING)
    private BoardRole boardRole; // Board 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // Board

    private String comment; // 내용

    @Enumerated(EnumType.STRING)
    private ReplyStatus status; // 댓글 상태

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정일

    @Builder
    public Reply(Integer id, User user, Board board, String comment, ReplyStatus status) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.comment = comment;
        this.status = status;
    }
}

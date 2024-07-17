package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
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
@Table(name = "bookmark_tb")
@Data
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 북마크 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // User

    @Enumerated(EnumType.STRING)
    private BoardRole boardRole; // Board 구분

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // Board

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성일

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정일

    @Builder
    public Bookmark(Integer id, User user, BoardRole boardRole, Board board) {
        this.id = id;
        this.user = user;
        this.boardRole = boardRole;
        this.board = board;
    }
}

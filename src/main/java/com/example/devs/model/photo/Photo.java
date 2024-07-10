package com.example.devs.model.photo;

import com.example.devs.model.board.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "photo_tb")
@Data
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 사진 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board; // Board

    private String fileName; // 사진 파일명

    private String filePath; // 사진 경로

    @Builder
    public Photo(Integer id, Board board, String fileName, String filePath) {
        this.id = id;
        this.board = board;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}

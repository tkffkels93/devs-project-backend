package com.example.devs.model.board;

import com.example.devs._core.enums.BoardStatus;
import com.example.devs._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class BoardResponse {

    // 게시글 개수 + 게시글 리스트 DTO
    @Data
    public static class BoardListDTO {
        private Integer totalBoardCount;
        private List<BoardList> boardList;

        public BoardListDTO(Integer totalBoardCount, List<BoardList> boardList) {
            this.totalBoardCount = totalBoardCount;
            this.boardList = boardList;
        }
    }

    // 게시글 리스트 DTO
    @Data
    public static class BoardList {
        private Integer id;
        private String nickname;
        private String title;
        private BoardStatus status;
        private String updatedAt;

        public BoardList(Board board) {
            this.id = board.getId();
            this.nickname = board.getUser().getNickname();
            this.title = board.getTitle();
            this.status = board.getStatus();
            this.updatedAt = LocalDateTimeFormatter.convert(
                    board.getUpdatedAt() != null ? board.getUpdatedAt() : board.getCreatedAt()
            );
        }
    }

    // 게시글 상세 보기
    @Data
    public static class BoardDetailDTO {
        private Integer id;
        private String nickname;
        private String title;
        private String content;
        private Integer hit;
        private BoardStatus status;
        private String updatedAt;

        public BoardDetailDTO(Board board) {
            this.id = board.getId();
            this.nickname = board.getUser().getNickname();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.hit = board.getHit();
            this.status = board.getStatus();
            this.updatedAt = LocalDateTimeFormatter.convert(
                    board.getUpdatedAt() != null ? board.getUpdatedAt() : board.getCreatedAt()
            );
        }
    }

}

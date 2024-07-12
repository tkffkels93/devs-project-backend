package com.example.devs.model.board;

import lombok.Data;

public class BoardResponse {
    @Data
    public static class ListDTO{
        private Integer boardId;
        private String boardTitle;
        private String boardContent;
        private Integer boardHit;
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private String userCreatedAt;

        public ListDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.boardContent = board.getContent();
            this.boardHit = board.getHit();
            this.userId = board.getUser().getId();
            this.userNickname = board.getUser().getNickname();
            this.userPosition = board.getUser().getPosition();
            this.userImage = board.getUser().getImage();
            this.userCreatedAt = board.getUser().getCreatedAt().toString();
        }
    }
}

package com.example.devs.model.board;

import lombok.Data;

import java.util.List;

public class BoardRequest {

    @Data
    public static class Update{
        private Integer boardId;
        private String title;
        private String content;
        private List<Base64Image> images;

        public Update(Integer boardId, String title, String content, List<Base64Image> images) {
            this.boardId = boardId;
            this.title = title;
            this.content = content;
            this.images = images;
        }
    }


    @Data
    public static class Write{
        private String title;
        private String content;
        private List<Base64Image> images;

        public Write(String title, String content, List<Base64Image> images) {
            this.title = title;
            this.content = content;
            this.images = images;
        }
    }

    @Data
    public static class Base64Image{
        private String imageData;
        private String fileName;
        public Base64Image(String imageData, String fileName) {
            this.imageData = imageData;
            this.fileName = fileName;
        }
    }
}

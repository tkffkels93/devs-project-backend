package com.example.devs.model.bookmark;

import com.example.devs._core.utils.LocalDateTimeFormatter;
import lombok.Data;

import java.util.List;

public class BookmarkResponse {

    // 북마크 리스트 DTO
    @Data
    public static class ListDTO {
        private Integer userId;  // 사용자 ID
        private Integer totalBookmarkCount; // 총 북마크 개수
        private List<BoardDTO> boardDTO; // 북마크 목록

        public ListDTO(Integer userId, Integer totalBookmarkCount, List<BoardDTO> boardDTO) {
            this.userId = userId;
            this.totalBookmarkCount = totalBookmarkCount;
            this.boardDTO = boardDTO;
        }
    }

    // 북마크한 게시글 DTO
    @Data
    public static class BoardDTO {
        private Integer userId;  // 게시글 작성자 ID
        private String userNickname;  // 게시글 작성자 닉네임
        private String userPosition;  // 게시글 작성자 직함
        private String userImage;  // 게시글 작성자 프로필 사진
        private Integer boardId;  // 게시글 ID
        private String boardTitle;  // 게시글 제목
        private String boardContent;  // 게시글 내용
        private Integer boardViews;  // 게시글 조회수
        private String boardCreatedAt;  // 게시글 생성일
        private Integer replyCount;  // 댓글 수
        private Integer loveCount; // 좋아요 수
        private boolean isLove;  // 좋아요 여부
        private boolean isBookmark;  // 북마크 여부 (항상 true)

        public BoardDTO(Bookmark bookmark, Integer loveCount, boolean isLove) {
            this.userId = bookmark.getBoard().getUser().getId();
            this.userNickname = bookmark.getBoard().getUser().getNickname();
            this.userPosition = bookmark.getBoard().getUser().getPosition();
            this.userImage = bookmark.getBoard().getUser().getImage();
            this.boardId = bookmark.getBoard().getId();
            this.boardTitle = bookmark.getBoard().getTitle();
            this.boardContent = bookmark.getBoard().getContent();
            this.boardViews = bookmark.getBoard().getHit();
            this.boardCreatedAt = LocalDateTimeFormatter.getDuration(bookmark.getBoard().getCreatedAt());
            this.replyCount = bookmark.getBoard().getReplies().size();
            this.loveCount = loveCount;
            this.isLove = isLove;
            this.isBookmark = true; // 북마크는 항상 true
        }
    }
}

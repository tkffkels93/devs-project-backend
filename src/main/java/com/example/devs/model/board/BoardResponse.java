package com.example.devs.model.board;

import com.example.devs._core.utils.LocalDateTimeFormatter;
import com.example.devs.model.photo.Photo;
import com.example.devs.model.reply.Reply;
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
        private String status;
        private String updatedAt;

        public BoardList(Board board) {
            this.id = board.getId();
            this.nickname = board.getUser().getNickname();
            this.title = board.getTitle();
            this.status = board.getStatus().getKorean();
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
        private String status;
        private String updatedAt;

        public BoardDetailDTO(Board board) {
            this.id = board.getId();
            this.nickname = board.getUser().getNickname();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.hit = board.getHit();
            this.status = board.getStatus().getKorean();
            this.updatedAt = LocalDateTimeFormatter.convert(
                    board.getUpdatedAt() != null ? board.getUpdatedAt() : board.getCreatedAt()
            );
        }
    }

    @Data
    public static class ListDTO {
        private Integer boardId;
        private String boardTitle;
        private String boardContent;
        private Integer boardHit;
        private String boardCreatedAt;
        private String boardCreatedAtDuration;
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private boolean myLike;
        private boolean myBookmark;
        private Long likeCount;
        private Long bookmarkCount;
        private Long replyCount;

        public ListDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.boardContent = board.getContent();
            this.boardHit = board.getHit();
            this.userId = board.getUser().getId();
            this.userNickname = board.getUser().getNickname();
            this.userPosition = board.getUser().getPosition();
            this.userImage = board.getUser().getImage();
            this.boardCreatedAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            this.boardCreatedAtDuration = LocalDateTimeFormatter.getDuration(board.getCreatedAt());
            this.myLike = false;
            this.myBookmark = false;
            this.likeCount = 0L;
            this.bookmarkCount = 0L;
            this.replyCount = 0L;
        }
    }

    @Data
    public static class Top10ListDTO {
        private Integer boardId;
        private String boardTitle;
        private String boardCreatedAt;
        private String boardCreatedAtDuration;
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private Integer rank;

        public Top10ListDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.userId = board.getUser().getId();
            this.userNickname = board.getUser().getNickname();
            this.userPosition = board.getUser().getPosition();
            this.userImage = board.getUser().getImage();
            this.boardCreatedAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            this.boardCreatedAtDuration = LocalDateTimeFormatter.getDuration(board.getCreatedAt());
            this.rank = 0;
        }
    }

    // 게시글 상세 보기
    @Data
    public static class DetailDTO {
        private Integer boardId;
        private String boardTitle;
        private String boardContent;
        private Integer boardHit;
        private String boardCreatedAt;
        private String boardCreatedAtDuration;
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private boolean myLike;
        private boolean myBookmark;
        private boolean isOwner;
        private Integer likeCount;
        private Long bookmarkCount;
        private Integer replyCount;
        private List<ReplyDTO> replies;
        private List<PhotoDTO> images;
        private String sessionUserImg;

        public DetailDTO(Board board, List<ReplyDTO> replies, List<PhotoDTO> images, Boolean myBookmark, Boolean myLike, Integer likeCount, String sessionUserImg) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.boardContent = board.getContent();
            this.boardHit = board.getHit();
            this.userId = board.getUser().getId();
            this.userNickname = board.getUser().getNickname();
            this.userPosition = board.getUser().getPosition();
            this.userImage = board.getUser().getImage();
            this.boardCreatedAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            this.boardCreatedAtDuration = LocalDateTimeFormatter.getDuration(board.getCreatedAt());
            this.myLike = myLike;
            this.myBookmark = myBookmark;
            this.isOwner = false;
            this.likeCount = likeCount;
            this.bookmarkCount = 0L;
            this.replyCount = replies.size();
            this.replies = replies;
            this.images = images;
            this.sessionUserImg = sessionUserImg;
        }
    }

    @Data
    public static class ReplyDTO {
        private Integer id;
        private Integer boardId; // Board
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private String comment; // 내용
        private String updatedAt; // 수정일
        private boolean isOwner;

        public ReplyDTO(Reply reply) {
            this.id = reply.getId();
            this.boardId = reply.getBoard().getId();
            this.userId = reply.getUser().getId();
            this.userNickname = reply.getUser().getNickname();
            this.userPosition = reply.getUser().getPosition();
            this.userImage = reply.getUser().getImage();
            this.comment = reply.getComment();
            this.updatedAt = LocalDateTimeFormatter.convert(
                    reply.getUpdatedAt() != null ? reply.getUpdatedAt() : reply.getCreatedAt()
            );
            this.isOwner = false;
        }
    }

    @Data
    public static class PhotoDTO{
        private Integer id;
        private String fileName;
        private String filePath;

        public PhotoDTO(Photo photo) {
            this.id = photo.getId();
            this.fileName = photo.getFileName();
            this.filePath = photo.getFilePath();
        }
    }

    // 검색한 게시판
    @Data
    public static class SearchBoardListDTO {
        private Integer boardId;
        private String boardTitle;
        private String boardContent;
        private Integer boardHit;
        private String boardCreatedAt;
        private String boardCreatedAtDuration;
        private Integer userId;
        private String userNickname;
        private String userPosition;
        private String userImage;
        private boolean myLike;
        private boolean myBookmark;
        private Long likeCount;
        private Long bookmarkCount;
        private Long replyCount;

        public  SearchBoardListDTO(Board board) {
            this.boardId = board.getId();
            this.boardTitle = board.getTitle();
            this.boardContent = board.getContent();
            this.boardHit = board.getHit();
            this.userId = board.getUser().getId();
            this.userNickname = board.getUser().getNickname();
            this.userPosition = board.getUser().getPosition();
            this.userImage = board.getUser().getImage();
            this.boardCreatedAt = LocalDateTimeFormatter.convert(board.getCreatedAt());
            this.boardCreatedAtDuration = LocalDateTimeFormatter.getDuration(board.getCreatedAt());
            this.myLike = false;
            this.myBookmark = false;
            this.likeCount = 0L;
            this.bookmarkCount = 0L;
            this.replyCount = 0L;
        }
    }
}

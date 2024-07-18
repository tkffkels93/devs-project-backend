package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import com.example.devs.model.like.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final LikeRepository likeRepository;

    // 회원 북마크 리스트
    public BookmarkResponse.ListDTO getBookmarkList(Integer userId, int page, int size) {
        Page<Bookmark> bookmarksPage = bookmarkRepository
                .findBookmarksByUserId(userId, BoardStatus.PUBLISHED, PageRequest.of(page - 1, size));
        List<Bookmark> bookmarks = bookmarksPage.getContent();


        List<BookmarkResponse.BoardDTO> boardDTOList = bookmarks.stream()
                .map(bookmark -> {
                    Integer loveCount = likeRepository.countByBoardId(bookmark.getBoard().getId(), BoardRole.Board, BoardStatus.PUBLISHED);
                    boolean isLove = likeRepository.existsByUserIdAndBoardId(userId, bookmark.getBoard().getId(), BoardRole.Board, BoardStatus.PUBLISHED);
                    boolean isBookmark = bookmarkRepository.existsByUserIdAndBoardId(userId, bookmark.getId());
                    return new BookmarkResponse.BoardDTO(bookmark, loveCount, isLove, isBookmark);
                })
                .collect(Collectors.toList());

        return new BookmarkResponse.ListDTO(userId, bookmarks.size(), boardDTOList);
    }

    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Boolean isBookmarked(BoardRole boardRole, Integer boardId, Integer userId) {
        return bookmarkRepository.isBookmarked(boardRole, boardId, userId);
    }
}

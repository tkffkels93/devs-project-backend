package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    // 회원 북마크 리스트
    public BookmarkResponse.ListDTO getBookmarkList(Integer userId, int page, int size) {
        Page<Bookmark> bookmarksPage = bookmarkRepository
                .findBookmarksByUserId(userId, PageRequest.of(page - 1, size, Sort.by("createdAt").descending()));
        List<Bookmark> bookmarks = bookmarksPage.getContent();

        List<BookmarkResponse.BoardDTO> boardDTOList = bookmarks.stream()
                .map(bookmark -> new BookmarkResponse.BoardDTO(bookmark, false))
                .collect(Collectors.toList());

        return new BookmarkResponse.ListDTO(userId, bookmarks.size(), boardDTOList);
    }

    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Integer getBookmarkCount(BoardRole boardRole, Integer boardId, Integer userId) {
        return bookmarkRepository.countBookmark(boardRole, boardId, userId);
    }
}

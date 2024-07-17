package com.example.devs.model.bookmark;

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
}

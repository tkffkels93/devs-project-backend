package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Integer getBookmarkCount(BoardRole boardRole, Integer boardId, Integer userId) {
        return bookmarkRepository.countBookmark(boardRole, boardId, userId);
    }
}

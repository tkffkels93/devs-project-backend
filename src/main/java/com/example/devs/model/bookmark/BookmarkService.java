package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs.model.board.Board;
import com.example.devs.model.board.BoardRepository;
import com.example.devs.model.like.LikeRepository;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import jakarta.transaction.Transactional;
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
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

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

    // 북마크 등록
    @Transactional
    public void addBookmark(Integer userId, Integer boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .board(board)
                .boardRole(board.getBoardRole())
                .build();

        bookmarkRepository.save(bookmark);
    }

    // 북마크 삭제
    @Transactional
    public void deleteBookmark(Integer userId, Integer boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));
        Bookmark bookmark = bookmarkRepository.findByUserIdAndBoardId(user.getId(), board.getId())
                .orElseThrow(() -> new Exception404("해당 북마크를 찾을 수 없습니다."));

        bookmarkRepository.delete(bookmark);
    }

    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Boolean isBookmarked(BoardRole boardRole, Integer boardId, Integer userId) {
        return bookmarkRepository.isBookmarked(boardRole, boardId, userId);
    }
}

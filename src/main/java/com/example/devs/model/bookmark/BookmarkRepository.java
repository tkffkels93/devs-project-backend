package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    //특정 게시글 & 특정 사용자의 북마크 개수를 가져온다.
    @Query("select (count(b) > 0) from Bookmark b where b.boardRole = :boardRole and b.board.id = :boardId and b.user.id = :userId")
    boolean isBookmarked(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);

    // 회원이 저장한 북마크 찾기
    @Query("SELECT bm FROM Bookmark bm JOIN FETCH bm.board b JOIN FETCH b.user u WHERE bm.user.id = :userId and b.status = :status ORDER BY b.createdAt DESC")
    Page<Bookmark> findBookmarksByUserId(@Param("userId") Integer userId, BoardStatus status, Pageable pageable);

    // 해당 해원의 북마크 여부 확인
    @Query("select (count(b) > 0) from Bookmark b where b.user.id = :userId and b.id = :bookmarkId")
    boolean existsByUserIdAndBoardId(@Param("userId") Integer userId, @Param("bookmarkId") Integer bookmarkId);
}
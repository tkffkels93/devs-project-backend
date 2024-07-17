package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    //특정 게시글 & 특정 사용자의 북마크 개수를 가져온다.
    @Query("select count(b) from Bookmark b where b.boardRole = :boardRole and b.board.id = :boardId and b.user.id = :userId")
    Integer countBookmark(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);

    // 회원이 저장한 북마크 찾기
    @Query("SELECT bm FROM Bookmark bm JOIN FETCH bm.board b JOIN FETCH b.user u WHERE bm.user.id = :userId ORDER BY b.createdAt DESC")
    Page<Bookmark> findBookmarksByUserId(@Param("userId") Integer userId, Pageable pageable);
}
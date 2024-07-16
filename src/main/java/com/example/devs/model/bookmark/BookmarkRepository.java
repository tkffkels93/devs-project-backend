package com.example.devs.model.bookmark;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    //특정 게시글 & 특정 사용자의 북마크 개수를 가져온다.
    @Query("select count(b) from Bookmark b where b.boardRole = :boardRole and b.board.id = :boardId and b.user.id = :userId")
    Integer countBookmark(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);
}
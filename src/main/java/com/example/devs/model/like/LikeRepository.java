package com.example.devs.model.like;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    //like 테이블에서 특정 게시글 & 특정 사용자의 좋아요 개수를 가져온다.
    @Query("select count(l) from Like l where l.boardRole = :boardRole and l.board.id = :boardId and l.user.id = :userId")
    Integer countLike(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);

    // 해당 게시글의 구분, 상태에 따른 좋아요 개수
    @Query("select count(l) from Like l where l.board.id = :boardId and l.board.BoardRole = :boardRole and l.board.status = :status")
    Integer countByBoardId(@Param("boardId") Integer boardId, @Param("boardRole") BoardRole boardRole, @Param("status") BoardStatus status);

    // 해당 게시글의 구분, 상태에 따른 좋아요 여부 확인
    @Query("select (count(l) > 0) from Like l where l.user.id = :userId and l.board.id = :boardId and l.board.BoardRole = :boardRole and l.board.status = :status")
    boolean existsByUserIdAndBoardId(@Param("userId") Integer userId, @Param("boardId") Integer boardId, @Param("boardRole") BoardRole boardRole, @Param("status") BoardStatus status);
}
package com.example.devs.model.like;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Integer> {

//    @Query("SELECT b, l " +
//            "FROM Board b " +
//            "JOIN Like l ON b.user.id = l.user.id AND b.id = l.board.id " +
//            "WHERE b.BoardRole = 'Board' AND l.boardRole = 'Board'")
        @Query("select count(l) from Like l where l.boardRole = :boardRole and l.board.id = :boardId and l.user.id = :userId")
        Integer countLike(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);

        @Query("select l from Like l where l.boardRole = :boardRole and l.board.id = :boardId and l.user.id = :userId")
        Like countLike2(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId, @Param("userId") Integer userId);
}
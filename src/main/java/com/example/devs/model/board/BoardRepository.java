package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 게시글 찾기
    @Query("select b from Board b join b.user u where b.BoardRole = :BoardRole order by b.id")
    List<Board> findByBoardRole(@Param("BoardRole") BoardRole BoardRole);
}
package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 게시글 찾기
    @Query("select b from Board b join b.user u where b.BoardRole = :BoardRole order by b.id")
    List<Board> findByBoardRole(@Param("BoardRole") BoardRole BoardRole);

    //게시글 목록 불러오기
    @Query("SELECT b from Board b where b.BoardRole = :boardRole")
    Page<Board> findAllByBoardRole(Pageable pageable, @Param("boardRole") BoardRole boardRole);

//    @Query("SELECT b, COUNT(l) " +
//            "FROM Board b " +
//            "JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole " +
//            "WHERE b.BoardRole = :boardRole AND b.id = :boardId " +
//            "GROUP BY b")
//    Page<Object[]> findAllByBoardRole(Pageable pageable, @Param("boardRole") BoardRole boardRole);
}
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

    //게시글 목록 불러오기, 좋아요와 북마크 개수를 조인해서 가져온다
    @Query("SELECT b, COUNT(DISTINCT l.id), COUNT(DISTINCT k.id) " +
            "FROM Board b " +
            "LEFT JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole " +
            "LEFT JOIN Bookmark k ON b.id = k.board.id AND k.boardRole = :boardRole " +
            "WHERE b.BoardRole = :boardRole " +
            "GROUP BY b " +
            "ORDER BY b.id DESC")
    Page<Object[]> findAllByBoardRole(Pageable pageable, @Param("boardRole") BoardRole boardRole);
}
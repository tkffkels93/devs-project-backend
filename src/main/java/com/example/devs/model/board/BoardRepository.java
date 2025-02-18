package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 게시글 찾기
    @Query("select b from Board b join b.user u where b.BoardRole = :BoardRole order by b.id")
    List<Board> findByBoardRole(@Param("BoardRole") BoardRole BoardRole);

    //게시글 목록 불러오기, 좋아요, 북마크, 댓글 개수를 조인해서 가져온다
    @Query("SELECT b, COUNT(DISTINCT l.id), COUNT(DISTINCT k.id), COUNT(DISTINCT r.id)" +
            "FROM Board b " +
            "LEFT JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole " +
            "LEFT JOIN Bookmark k ON b.id = k.board.id AND k.boardRole = :boardRole " +
            "LEFT JOIN Reply r ON b.id = r.board.id AND r.boardRole = :boardRole " +
            "WHERE b.BoardRole = :boardRole " +
            "GROUP BY b " +
            "ORDER BY b.id DESC")
    Page<Object[]> findAllByBoardRole(Pageable pageable, @Param("boardRole") BoardRole boardRole);

    //인기게시글 top10불러오기 (조회수 높은 순, 그다음 좋아요 횟수 기준)
    @Query("SELECT b " +
            "FROM Board b " +
            "LEFT JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole " +
            "WHERE b.BoardRole = :boardRole " +
            "GROUP BY b " +
            "ORDER BY b.hit DESC, COUNT(DISTINCT l.id) DESC " +
            "LIMIT 10")
    List<Board> findTop10ByBoardRole(@Param("boardRole") BoardRole boardRole);

    //특정 게시글 불러오기, 좋아요와 북마크 개수를 조인해서 가져온다 (댓글은 서비스에서 전체를 가져올거기때문에 갯수필요없음)
    @Query("SELECT b, COUNT(DISTINCT l.id), COUNT(DISTINCT k.id) " +
            "FROM Board b " +
            "LEFT JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole " +
            "LEFT JOIN Bookmark k ON b.id = k.board.id AND k.boardRole = :boardRole " +
            "WHERE b.BoardRole = :boardRole and b.id=:boardId " +
            "GROUP BY b")
    Optional<Board> findByBoardRoleAndId(@Param("boardRole") BoardRole boardRole, @Param("boardId") Integer boardId);

    // 특정 사용자의 게시글 조회하기
    @Query("SELECT b FROM Board b WHERE b.user.id = :userId ORDER BY b.createdAt DESC")
    Page<Board> findByUserId(Integer userId, Pageable pageable);

    // 특정 사용자의 게시글 수 조회
    @Query("SELECT COUNT(b.id) FROM Board b WHERE b.user.id = :userId")
    Integer findBoardCountByUserId(Integer userId);

    // 검색한 게시글
    @Query("""
       SELECT b, COUNT(DISTINCT l.id), COUNT(DISTINCT k.id), COUNT(DISTINCT r.id)
       FROM Board b
       LEFT JOIN Like l ON b.id = l.board.id AND l.boardRole = :boardRole
       LEFT JOIN Bookmark k ON b.id = k.board.id AND k.boardRole = :boardRole
       LEFT JOIN Reply r ON b.id = r.board.id AND r.boardRole = :boardRole
       WHERE b.BoardRole = :boardRole AND LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
       GROUP BY b
       ORDER BY b.id DESC
       """)
    Page<Object[]> findBoardByQuery(Pageable pageable, @Param("boardRole") BoardRole boardRole, @Param("query") String query);
}
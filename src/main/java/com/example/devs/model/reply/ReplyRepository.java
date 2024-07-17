package com.example.devs.model.reply;

import com.example.devs._core.enums.BoardRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    //특정 게시글의 댓글 가져오기
    @Query("SELECT r FROM Reply r where r.board.id = :boardId and r.boardRole=:boardRole order by r.id desc")
    List<Reply> findByBoardId(BoardRole boardRole, Integer boardId);
}
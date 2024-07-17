package com.example.devs.model.reply;

import com.example.devs._core.enums.BoardRole;
import com.example.devs.model.board.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    public List<BoardResponse.ReplyDTO> getReplies(BoardRole boardRole, Integer boardId) {
        List<Reply> replies = replyRepository.findByBoardId(boardRole, boardId);
        return replies.stream().map(BoardResponse.ReplyDTO::new).toList();
    }
}

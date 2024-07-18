package com.example.devs.model.reply;

import com.example.devs._core.enums.BoardRole;
import com.example.devs.model.board.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    public List<BoardResponse.ReplyDTO> getReplies(BoardRole boardRole, Integer boardId, Integer userId) {
        List<Reply> replies = replyRepository.findByBoardId(boardRole, boardId);
        return replies.stream().map( reply -> {
            BoardResponse.ReplyDTO dto = new BoardResponse.ReplyDTO(reply);
            dto.setOwner(Objects.equals(dto.getUserId(), userId));//내댓글이면
            return dto;
        }).toList();
    }
}

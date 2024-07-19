package com.example.devs.model.reply;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.ReplyStatus;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs.model.board.Board;
import com.example.devs.model.board.BoardRepository;
import com.example.devs.model.board.BoardResponse;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<BoardResponse.ReplyDTO> getReplies(BoardRole boardRole, Integer boardId, Integer userId) {
        List<Reply> replies = replyRepository.findByBoardId(boardRole, boardId);
        return replies.stream().map(reply -> {
            BoardResponse.ReplyDTO dto = new BoardResponse.ReplyDTO(reply);
            dto.setOwner(Objects.equals(dto.getUserId(), userId));//내댓글이면
            return dto;
        }).toList();
    }

    // 댓글 작성
    @Transactional
    public void createReply(Integer userId, Integer boardId, ReplyRequest.ReplySaveDTO reqDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        Reply reply = Reply.builder()
                .comment(reqDTO.getComment())
                .user(user)
                .board(board)
                .boardRole(board.getBoardRole())
                .status(ReplyStatus.PUBLISHED)
                .build();

        replyRepository.save(reply);
    }

    // 댓글 수정
    @Transactional
    public void updateReply(Integer userId, Integer replyId, String comment) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new Exception404("해당 댓글을 찾을 수 없습니다."));

        if (!Objects.equals(user.getId(), reply.getUser().getId())) {
            throw new Exception403("해당 댓글을 수정할 권한이 없습니다.");
        }

        reply.setComment(comment);

        replyRepository.save(reply);
    }

    // 댓글 삭제
    @Transactional
    public void deleteReply(Integer userId, Integer replyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new Exception404("해당 댓글을 찾을 수 없습니다."));

        if (!Objects.equals(user.getId(), reply.getUser().getId())) {
            throw new Exception403("해당 댓글을 삭제할 권한이 없습니다.");
        }

        reply.setStatus(ReplyStatus.DELETED);

        replyRepository.delete(reply);
    }
}

package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.enums.BoardStatus;
import com.example.devs._core.errors.exception.Exception400;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception403;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시글 리스트 (관리자)
    public BoardResponse.BoardListDTO getBoardList(User sessionAdmin) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        List<Board> boardDTO = boardRepository.findByBoardRole(BoardRole.Board);
        Integer totalBoardCount = boardDTO.size();
        List<BoardResponse.BoardList> boardList = boardDTO.stream().map(BoardResponse.BoardList::new).toList();

        return new BoardResponse.BoardListDTO(totalBoardCount, boardList);
    }

    // 게시글 상세 정보 (관리자)
    public BoardResponse.BoardDetailDTO getBoardDetail(User sessionAdmin, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionAdmin)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시물을 찾을 수 없습니다."));

        return new BoardResponse.BoardDetailDTO(board);
    }

    // 게시글 숨김
    @Transactional
    public void hideBoard(User sessionUser, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionUser)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (board.getStatus() == BoardStatus.HIDE) {
            throw new Exception400("이미 숨긴 게시글입니다.");
        }

        board.setStatus(BoardStatus.HIDE);
        boardRepository.save(board);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(User sessionUser, Integer boardId) {
        userRepository.findById(
                Optional.ofNullable(sessionUser)
                        .orElseThrow(() -> new Exception401("로그인 후 이용 바랍니다."))
                        .getId()
        ).orElseThrow(() -> new Exception403("관리자만 접근할 수 있습니다."));

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (board.getStatus() == BoardStatus.DELETED) {
            throw new Exception400("이미 삭제된 게시글입니다.");
        }

        board.setStatus(BoardStatus.DELETED);
        boardRepository.save(board);
    }
}

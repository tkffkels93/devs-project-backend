package com.example.devs.model.like;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.errors.exception.Exception401;
import com.example.devs._core.errors.exception.Exception404;
import com.example.devs.model.board.Board;
import com.example.devs.model.board.BoardRepository;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Boolean isLiked(BoardRole boardRole, Integer boardId, Integer userId) {
        return likeRepository.isLiked(boardRole, boardId, userId);
    }

    // 좋아요 등록
    @Transactional
    public void addLike(Integer userId, Integer boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));

        Like like = Like.builder()
                .user(user)
                .board(board)
                .boardRole(board.getBoardRole())
                .build();

        likeRepository.save(like);
    }

    // 좋아요 취소
    @Transactional
    public void deleteLike(Integer userId, Integer boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception401("로그인 후 이용 가능합니다."));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다."));
        Like like = likeRepository.findByUserIdAndBoardId(user.getId(), board.getId())
                .orElseThrow(() -> new Exception404("해당 북마크를 찾을 수 없습니다."));

        likeRepository.delete(like);
    }
}

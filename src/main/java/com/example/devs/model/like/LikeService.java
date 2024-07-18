package com.example.devs.model.like;

import com.example.devs._core.enums.BoardRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    //해당 게시글에 특정 사용자의 like 개수를 가져온다 (여부가져오기)
    public Boolean isLiked(BoardRole boardRole, Integer boardId, Integer userId) {
        return likeRepository.isLiked(boardRole, boardId, userId);
    }
}

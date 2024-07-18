package com.example.devs.model.like;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/like")
@RestController
public class LikeRestController {
    private final LikeService likeService;
    private final HttpSession session;

    // 좋아요 등록
    @PostMapping("/{boardId}")
    public ResponseEntity<?> addLike(@PathVariable Integer boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        likeService.addLike(sessionUser.getId(), boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("좋아요 추가 성공"));
    }

    // 좋아요 취소
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteLike(@PathVariable Integer boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        likeService.deleteLike(sessionUser.getId(), boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("좋아요 취소 성공"));
    }
}

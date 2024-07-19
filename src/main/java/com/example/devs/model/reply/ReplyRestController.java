package com.example.devs.model.reply;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/reply")
@RestController
public class ReplyRestController {
    private final ReplyService replyService;
    private final HttpSession session;

    // 댓글 작성
    @PostMapping("/{boardId}")
    public ResponseEntity<?> createReply(@PathVariable Integer boardId, @RequestBody ReplyRequest.ReplySaveDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        replyService.createReply(sessionUser.getId(), boardId, reqDTO);
        return ResponseEntity.ok(new ApiUtil<>("댓글 작성 성공"));
    }

    // 댓글 수정
    @PutMapping("/{replyId}/edit")
    public ResponseEntity<?> updateReply(@PathVariable Integer replyId, @RequestBody String comment) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        replyService.updateReply(sessionUser.getId(), replyId, comment);
        return ResponseEntity.ok(new ApiUtil<>("댓글 수정 성공"));
    }

    // 댓글 삭제
    @PutMapping("/{replyId}/delete")
    public ResponseEntity<?> deleteReply(@PathVariable Integer replyId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        replyService.deleteReply(sessionUser.getId(), replyId);
        return ResponseEntity.ok(new ApiUtil<>("댓글 삭제 성공"));
    }
}

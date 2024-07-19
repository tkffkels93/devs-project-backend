package com.example.devs.model.bookmark;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
@RestController
public class BookmarkRestController {
    private final BookmarkService bookmarkService;
    private final HttpSession session;

    // 북마크 리스트
    @GetMapping("/list")
    public ResponseEntity<?> getBookmarkList(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        BookmarkResponse.ListDTO BookmarkList = bookmarkService.getBookmarkList(sessionUser.getId(), page, size);
        return ResponseEntity.ok().body(new ApiUtil<>(BookmarkList));
    }

    // 북마크 등록
    @PostMapping("/{boardId}")
    public ResponseEntity<?> addBookmark(@PathVariable Integer boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        bookmarkService.addBookmark(sessionUser.getId(), boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("북마크 추가 성공"));
    }

    // 북마크 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBookmark(@PathVariable Integer boardId) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        bookmarkService.deleteBookmark(sessionUser.getId(), boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("북마크 삭제 성공"));
    }
}

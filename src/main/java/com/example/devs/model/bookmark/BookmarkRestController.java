package com.example.devs.model.bookmark;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

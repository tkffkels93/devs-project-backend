package com.example.devs.model.board;

import com.example.devs._core.utils.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardRestController {
    private final BoardService boardService;

    @GetMapping("/list")
    //게시글 목록 가져오기 - page(페이지수), size(한페이지당글개수)
    public ResponseEntity<?> boardList(HttpServletRequest request, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<BoardResponse.ListDTO> boardList = boardService.getBoards(page-1, size);
        return ResponseEntity.ok(new ApiUtil<>(boardList)); //회원가입성공
    }
}

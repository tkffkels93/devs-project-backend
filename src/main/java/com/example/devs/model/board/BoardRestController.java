package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardRestController {
    private final BoardService boardService;

    @GetMapping("/list")
    //게시글 목록 가져오기 - page(페이지수), size(한페이지당글개수)
    public ResponseEntity<?> boardList(HttpServletRequest request,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "Board") BoardRole boardRole) {
        //현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        Page<BoardResponse.ListDTO> boardList = boardService.getBoards(page-1, size, boardRole, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(boardList));
    }

    //인기게시글 (top10) 가져오기
    @GetMapping("/top10List")
    public ResponseEntity<?> boardTop10List(HttpServletRequest request) {
        List<BoardResponse.Top10ListDTO> boardList = boardService.getTop10Boards(BoardRole.Board);
        return ResponseEntity.ok(new ApiUtil<>(boardList));
    }
}

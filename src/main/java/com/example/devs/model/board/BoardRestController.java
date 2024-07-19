package com.example.devs.model.board;

import com.example.devs._core.enums.BoardRole;
import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.user.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<?> boardTop10List() {
        List<BoardResponse.Top10ListDTO> boardList = boardService.getTop10Boards(BoardRole.Board);
        return ResponseEntity.ok(new ApiUtil<>(boardList));
    }

    @GetMapping("/{boardId}/detail")
    public ResponseEntity<?> boardDetail(HttpServletRequest request, @PathVariable Integer boardId) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO dto = boardService.getBoardDetail(BoardRole.Board, boardId, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(dto));
    }

    @PostMapping("/write")
    public ResponseEntity<?> boardWrite(HttpServletRequest request, @RequestBody BoardRequest.Write writeDto) throws IOException {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardService.writeBoard(sessionUser.getId(),writeDto);
        return ResponseEntity.ok(new ApiUtil<>(null));
    }

    // 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<?> getSearchBoard(HttpServletRequest request,
                                            @RequestParam String query,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "Board") BoardRole boardRole){
        Pageable pageable = PageRequest.of(page - 1, size); // 클라이언트는 1부터 시작, 서버는 0부터 시작
        //현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        if (query.isEmpty()){
            return ResponseEntity.badRequest().body(new ApiUtil<>(404, "검색어를 입력해주세요."));
        }

        Page<BoardResponse.SearchBoardListDTO> boardList = boardService.getBoardsBySearch(pageable , boardRole, sessionUser.getId(), query);
        return ResponseEntity.ok(new ApiUtil<>(boardList));
    }

}

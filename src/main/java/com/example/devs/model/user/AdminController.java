package com.example.devs.model.user;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs.model.board.BoardResponse;
import com.example.devs.model.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController // TODO 화면 나오면 전환 예정
public class AdminController {
    private final UserService userService;
    private final BoardService boardService;
    private final HttpSession session;

    // test
    @GetMapping("/test")
    public @ResponseBody String test(HttpServletRequest request) {
        System.out.println("test");
        return "test";
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.AdminLoginDTO requestDTO) {
        User admin = userService.adminLogin(requestDTO);
        session.setAttribute("sessionAdmin", admin);
        UserResponse.AdminLoginDTO responseDTO = new UserResponse.AdminLoginDTO(admin);
        return ResponseEntity.ok().body(new ApiUtil<>(responseDTO));
    }

    // 회원 정보 리스트 페이지
    @GetMapping("/users")
    public ResponseEntity<?> getUserList() {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        UserResponse.UserListDTO userList = userService.getUserList(sessionAdmin);
        return ResponseEntity.ok().body(new ApiUtil<>(userList));
    }

    // 회원 상세 정보
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserDetail(@PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        UserResponse.DetailDTO userDetail = userService.getUserDetail(sessionAdmin, userId);
        return ResponseEntity.ok().body(new ApiUtil<>(userDetail));
    }

    // 회원 삭제
    @PostMapping("/users/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        userService.deleteUser(sessionAdmin, userId);
        return ResponseEntity.ok().body(new ApiUtil<>("회원 삭제 성공"));
    }

    // 회원 차단
    @PostMapping("/users/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        userService.blockUser(sessionAdmin, userId);
        return ResponseEntity.ok().body(new ApiUtil<>("회원 차단 성공"));
    }

    // 게시글 리스트 페이지
    @GetMapping("/boards")
    public ResponseEntity<?> getBoardList() {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        BoardResponse.BoardListDTO boardList = boardService.getBoardList(sessionAdmin);
        return ResponseEntity.ok().body(new ApiUtil<>(boardList));
    }

    // 게시글 상세 정보
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<?> getBoardDetail(@PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        BoardResponse.BoardDetailDTO boardDetail = boardService.getBoardDetail(sessionAdmin, boardId);
        return ResponseEntity.ok().body(new ApiUtil<>(boardDetail));
    }

    // 게시글 숨김
    @PostMapping("/boards/{boardId}/hide")
    public ResponseEntity<?> hideBoard(@PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        boardService.hideBoard(sessionAdmin, boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("게시글 숨김 성공"));
    }

    // 게시글 삭제
    @PostMapping("/boards/{boardId}/delete")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        boardService.deleteBoard(sessionAdmin, boardId);
        return ResponseEntity.ok().body(new ApiUtil<>("게시글 삭제 성공"));
    }
}

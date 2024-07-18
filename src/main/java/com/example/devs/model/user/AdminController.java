package com.example.devs.model.user;

import com.example.devs.model.board.BoardResponse;
import com.example.devs.model.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller // TODO 화면 나오면 전환 예정
public class AdminController {
    private final UserService userService;
    private final BoardService boardService;
    private final HttpSession session;

    // 로그인 페이지
    @GetMapping({"", "/login-page"})
    public String loginPage() {
        return "admin/loginPage";
    }

    // 로그인
    @PostMapping("/login")
    public String login(UserRequest.AdminLoginDTO requestDTO) {
        User admin = userService.adminLogin(requestDTO);
        session.setAttribute("sessionAdmin", admin);
        return "redirect:/admin/users";
    }

    // 회원 정보 리스트 페이지
    @GetMapping("/users")
    public String getUserList(HttpServletRequest request) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        UserResponse.UserListDTO userListDTO = userService.getUserList(sessionAdmin);
        request.setAttribute("userListDTO", userListDTO);
        return "admin/user/userListPage";
    }

    // 회원 상세 정보
    @GetMapping("/users/{userId}")
    public String getUserDetail(HttpServletRequest request, @PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        UserResponse.DetailDTO userDetail = userService.getUserDetail(sessionAdmin, userId);
        request.setAttribute("userDetail", userDetail);
        return "admin/user/userDetailPage";
    }

    // 회원 삭제
    @PostMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        userService.deleteUser(sessionAdmin, userId);
        return "redirect:/admin/users";
    }

    // 회원 차단
    @PostMapping("/users/{userId}/block")
    public String blockUser(@PathVariable Integer userId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        userService.blockUser(sessionAdmin, userId);
        return "redirect:/admin/users";
    }

    // 게시글 리스트 페이지
    @GetMapping("/boards")
    public String getBoardList(HttpServletRequest request) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        BoardResponse.BoardListDTO boardListDTO = boardService.getBoardList(sessionAdmin);
        request.setAttribute("boardListDTO", boardListDTO);
        return "admin/board/boardListPage";
    }

    // 게시글 상세 정보
    @GetMapping("/boards/{boardId}")
    public String getBoardDetail(HttpServletRequest request, @PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        BoardResponse.BoardDetailDTO boardDetail = boardService.getBoardDetail(sessionAdmin, boardId);
        request.setAttribute("boardDetail", boardDetail);
        return "admin/board/boardDetailPage";
    }

    // 게시글 숨김
    @PostMapping("/boards/{boardId}/hide")
    public String hideBoard(HttpServletRequest request, @PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        boardService.hideBoard(sessionAdmin, boardId);
        return "redirect:/admin/boards";
    }

    // 게시글 삭제
    @PostMapping("/boards/{boardId}/delete")
    public String deleteBoard(HttpServletRequest request, @PathVariable Integer boardId) {
        User sessionAdmin = (User) session.getAttribute("sessionAdmin");
        boardService.deleteBoard(sessionAdmin, boardId);
        return "redirect:/admin/boards";
    }
}

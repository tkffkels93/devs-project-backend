package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.utils.ApiUtil;
import com.example.devs._core.utils.JwtVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {
    private final UserService userService;

    // https://kauth.kakao.com/oauth/authorize?redirect_uri=http://localhost:8080/api/users/oauth/kakao&response_type=code&client_id=3e811404984aeead4e15eeeb1393907f
    // https://nid.naver.com/oauth2.0/authorize?redirect_uri=http://localhost:8080/api/users/oauth/naver&response_type=code&client_id=nfdBh7_HSSjdAvaBPLWs
    // OAuth 로그인
    @GetMapping("/oauth/{provider}")
    public ResponseEntity<?> oauthLogin(@PathVariable("provider") String provider, @RequestParam("accessToken") String accessToken) {
        UserProvider userProvider;
        userProvider = UserProvider.valueOf(provider.toUpperCase());
        String jwt = userService.oauthLogin(userProvider, accessToken);
        System.out.println("########### JWT ###########: " + jwt.toString());
        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX + jwt)
                .body(new ApiUtil<>(null));
    }
    // OAuth 연동 해제
    @PostMapping("/unlink/{provider}")
    public ResponseEntity<?> oauthUnlink(HttpServletRequest request, @PathVariable("provider") String provider){
        UserProvider userProvider;
        userProvider = UserProvider.valueOf(provider.toUpperCase());

        //현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        userService.oauthUnlink(userProvider, sessionUser.getId());
        return ResponseEntity.ok().body(new ApiUtil<>(null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO loginDTO){
        String jwt = userService.login(loginDTO);
        System.out.println("########### JWT ###########: " + jwt.toString());
        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX+ jwt)
                .body(new ApiUtil<>(null)); // header 문법
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserRequest.JoinDTO joinDTO){
        User savedUser = userService.join(joinDTO);
        if(savedUser != null && savedUser.getId()!=null){
            return ResponseEntity.ok(new ApiUtil<>(null)); //회원가입성공
        }else return ResponseEntity.ok(new ApiUtil<>(201, "회원 가입 실패")); // header 문법

    }

    @GetMapping("/mypage")
    public ResponseEntity<?> getMyInfo(HttpServletRequest request,
                                       @RequestParam(defaultValue = "boards") String type,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size); // 클라이언트는 1부터 시작, 서버는 0부터 시작

        // 현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // 게시글 요청 시
        if (type.equals("boards")) {
            if (page == 1) {
                // 최초 요청 시 유저 프로필 정보와 첫 페이지의 게시글 정보를 반환
                UserResponse.MypageDTO mypageDTO = userService.getMyInfo(sessionUser.getId(), pageable, type);
                return ResponseEntity.ok().body(new ApiUtil<>(mypageDTO));
            } else {
                // 다음 페이지 게시글만 반환
                List<UserResponse.MyBoardListDTO> myBoardListDTO = userService.getBoardPage(sessionUser.getId(), pageable);
                return ResponseEntity.ok().body(new ApiUtil<>(myBoardListDTO));
            }

        // 댓글 요청 시
        } else if (type.equals("replies")) {
            // 내가 단 댓글만 반환
            List<UserResponse.MyReplyListDTO> myReplyListDTO = userService.getMyReplies(sessionUser.getId(), pageable);
            return ResponseEntity.ok().body(new ApiUtil<>(myReplyListDTO));
        } else {
            return ResponseEntity.badRequest().body(new ApiUtil<>(400, "유효하지 않은 요청"));
        }
    }

    // 사용자 프로필 조회
    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Integer userId,
                                            @RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size); // 클라이언트는 1부터 시작, 서버는 0부터 시작
        UserResponse.UserProfileDTO userProfileDTO = userService.getUserProfile(userId, pageable);
        return ResponseEntity.ok().body(new ApiUtil<>(userProfileDTO));
    }

    // 프로필 수정 데이터 전달
    @GetMapping("/profile/update")
    public ResponseEntity<?> getUpdateProfileInfo(HttpServletRequest request) {
        //현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // 사용자 데이터 조회
        UserResponse.UpdateProfileInfoDTO updateProfileInfoDTO = userService.getUpdateProfileInfo(sessionUser.getId());

        return ResponseEntity.ok().body(new ApiUtil<>(updateProfileInfoDTO));
    }

    // 사용자 프로필 수정
    @PutMapping("/profile/update")
    public ResponseEntity<?> updateProfile(HttpServletRequest request,
                                           @RequestBody UserRequest.UpdateProfileDTO updateProfileDTO) throws IOException {
        //현재 접속한 사용자 아이디 가져오기
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");

        // 업데이트
        UserResponse.UpdateProfileInfoDTO updateProfileInfoDTO = userService.updateProfile(sessionUser.getId(), updateProfileDTO);

        return ResponseEntity.ok(new ApiUtil<>(updateProfileInfoDTO));
    }
}

package com.example.devs.model.user;

import com.example.devs._core.enums.UserProvider;
import com.example.devs._core.utils.ApiUtil;
import com.example.devs._core.utils.JwtVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {
    private final UserService userService;

    // https://kauth.kakao.com/oauth/authorize?redirect_uri=http://localhost:8080/api/users/oauth/kakao&response_type=code&client_id=3e811404984aeead4e15eeeb1393907f
    // https://nid.naver.com/oauth2.0/authorize?redirect_uri=http://localhost:8080/api/users/oauth/naver&response_type=code&client_id=nfdBh7_HSSjdAvaBPLWs
    // OAuth 로그인
    @GetMapping("/oauth/{provider}")
    public ResponseEntity<?> oauthLogin(@PathVariable("provider") String provider, @RequestParam("code") String code) {
        UserProvider userProvider;
        userProvider = UserProvider.valueOf(provider.toUpperCase());
        String jwt = userService.oauthLogin(userProvider, code);
        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX + jwt)
                .body(new ApiUtil<>(null));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDTO loginDTO){
        String jwt = userService.login(loginDTO);
        return ResponseEntity.ok()
                .header(JwtVO.HEADER, JwtVO.PREFIX+ jwt)
                .body(new ApiUtil<>(null)); // header 문법
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(UserRequest.JoinDTO joinDTO){
        User savedUser = userService.join(joinDTO);
        if(savedUser != null && savedUser.getId()!=null){
            return ResponseEntity.ok(new ApiUtil<>(null)); //회원가입성공
        }else return ResponseEntity.ok(new ApiUtil<>(201, "회원 가입 실패")); // header 문법

    }


}

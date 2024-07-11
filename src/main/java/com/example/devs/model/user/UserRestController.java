package com.example.devs.model.user;

import com.example.devs._core.utils.ApiUtil;
import com.example.devs._core.utils.JwtVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserRestController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(UserRequest.LoginDTO loginDTO){
        String jwt = userService.login(loginDTO);
        return ResponseEntity.ok().header(JwtVO.HEADER, JwtVO.PREFIX+ jwt).body(new ApiUtil<>(null)); // header 문법
    }
}

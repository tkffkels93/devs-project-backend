package com.example.devs.model.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;

    @GetMapping("/test")
    public @ResponseBody  String test(HttpServletRequest request) {
        System.out.println("test");
        return "test";
    }
}

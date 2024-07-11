package com.example.devs.model.user;

import com.example.devs._core.errors.exception.Exception404;
import com.example.devs._core.utils.EncryptUtil;
import com.example.devs._core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public String login(UserRequest.LoginDTO loginDTO){
        String msg="아이디 혹은 비밀번호가 잘못되었습니다.";
        System.out.println(loginDTO.getEmail());
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new Exception404(msg));
        //비밀번호 비교
        if (EncryptUtil.checkPw(loginDTO.getPassword(), user.getPassword())) {//비밀번호일치
            return JwtUtil.userCreate(user); // jwt토큰 생성 후 반환
        }else throw new Exception404(msg); //일치하지않는경우
    }
}

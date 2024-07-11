package com.example.devs;

import com.example.devs._core.utils.EncryptUtil;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        User user = userRepository.findByEmail("ssar@nate.com").orElseThrow();;
        System.out.println("user = " + user);

        System.out.println( EncryptUtil.hashPw("1234") );
//        List<User> userList = userRepository.findAll();
//        System.out.println("userList = " + userList);
    }
}

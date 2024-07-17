package com.example.devs;

import com.example.devs._core.utils.EncryptUtil;
import com.example.devs.model.board.Board;
import com.example.devs.model.reply.Reply;
import com.example.devs.model.user.User;
import com.example.devs.model.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    public void findMyBoardsById_Test() {
        // given
        int userId = 1;
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Board> myBoards = userRepository.findMyBoardsById(userId, pageable);

        // then
        myBoards.forEach(board -> {
            System.out.println("Board ID: " + board.getId());
            System.out.println("Title: " + board.getTitle());
            System.out.println("Created At: " + board.getCreatedAt());
        });
    }

    @Test
    public void findMyRepliesById_Test() {
        // given
        int userId = 1;
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Reply> myReplies = userRepository.findMyRepliesById(userId, pageable);

        // then
        myReplies.forEach(reply -> {
            System.out.println("Reply ID: " + reply.getId());
            System.out.println("Comment: " + reply.getComment());
            System.out.println("Board Title: " + reply.getBoard().getTitle());
            System.out.println("Created At: " + reply.getCreatedAt());
        });
    }

}

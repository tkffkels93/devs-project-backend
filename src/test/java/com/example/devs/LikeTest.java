package com.example.devs;

import com.example.devs._core.enums.BoardRole;
import com.example.devs.model.like.Like;
import com.example.devs.model.like.LikeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
public class LikeTest {
    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void test() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(1, 10, sort);
        Integer count = likeRepository.countLike(BoardRole.Board, 1, 4);
        System.out.println("count = " + count);

        Like like = likeRepository.countLike2(BoardRole.Board, 1, 4);
        System.out.println("like = " + like.getId());
    }
}

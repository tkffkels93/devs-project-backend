package com.example.devs;

import com.example.devs.model.photo.PhotoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PhotoTest {
    @Autowired
    private PhotoRepository photoRepository;

    @Test
    public void test() {
        photoRepository.findByBoardId(1);
    }
}

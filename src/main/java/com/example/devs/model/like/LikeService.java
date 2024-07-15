package com.example.devs.model.like;

import com.example.devs.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final UserRepository userRepository;
}

package com.example.devs.model.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }
}

package com.example.devs.model.photo;

import com.example.devs.model.board.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    public Photo savePhoto(Photo photo) {
        return photoRepository.save(photo);
    }
    public List<BoardResponse.PhotoDTO> getPhotos(Integer boardId) {
        List<Photo> photos = photoRepository.findByBoardId(boardId);
        return photos.stream().map(BoardResponse.PhotoDTO::new).toList();
    }
}

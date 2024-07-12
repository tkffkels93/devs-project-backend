package com.example.devs.model.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Page<BoardResponse.ListDTO> getBoards(int page, int size){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Board> boards =  boardRepository.findAll(pageable);
        return boards.map(BoardResponse.ListDTO::new);
    }
}

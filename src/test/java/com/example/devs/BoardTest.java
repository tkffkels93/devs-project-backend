package com.example.devs;

import com.example.devs._core.enums.BoardRole;
import com.example.devs.model.board.Board;
import com.example.devs.model.board.BoardRepository;
import com.example.devs.model.board.BoardResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> boards = boardRepository.findAllByBoardRole(pageable, BoardRole.Board);

        Page<BoardResponse.ListDTO> boards2 = boards.map(BoardResponse.ListDTO::new);
                boards2.forEach(
                        System.out::println
        );

//        Page<Board> boards =  boardRepository.findAll(pageable);
//        Page<BoardResponse.ListDTO> boards2 = boards.map(BoardResponse.ListDTO::new);
//        boards2.forEach(
//                board -> System.out.println(board)
//        );

//        Page<BoardResponse.ListDTO> boards.map(BoardResponse.ListDTO::new).stream().toList();
//
//        Page<Board> boards = boardRepository.findAll(pageable);
//        Page<BoardDTO> boardDTOs = boards.map(BoardMapper::toDto);


//        boards.forEach(System.out::println);
//        System.out.println("boards = " + boards);

    }
}

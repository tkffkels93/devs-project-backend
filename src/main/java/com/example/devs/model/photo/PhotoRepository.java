package com.example.devs.model.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    @Query("SELECT p FROM Photo p where p.board.id = :boardId order by p.id asc")
    List<Photo> findByBoardId(Integer boardId);
}
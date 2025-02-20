package com.example.board.Repository;

import com.example.board.Entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}

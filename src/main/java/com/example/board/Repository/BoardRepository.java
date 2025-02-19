package com.example.board.Repository;

import com.example.board.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}

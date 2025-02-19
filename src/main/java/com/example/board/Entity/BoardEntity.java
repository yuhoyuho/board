package com.example.board.Entity;

// DB의 테이블 역할을 하는 클래스 SpringData JPA에서는 거의 필수적으로 사용

import com.example.board.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    @Id //pk 컬럼 지정. 최소 1개는 필수로 있어야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column // default : 크기 = 255, null ok
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContent;

    @Column
    private int boardHits;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardHits(0);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardHits(boardDTO.getBoardHits());

        return boardEntity;
    }
}

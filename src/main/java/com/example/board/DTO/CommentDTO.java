package com.example.board.DTO;

import com.example.board.Entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        // Service 클래스에서 boardId를 같이 넘겨받고 "setBoardId(boardId)" 로 작성해도 됨
        // 이 경우 Service 메서드에서 @Transactional을 붙여줘야함
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());

        return commentDTO;
    }
}

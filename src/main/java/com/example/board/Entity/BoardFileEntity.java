package com.example.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType은 보통 LAZY로 사용
    @JoinColumn(name = "board_id") // DB에 만들어지는 column 이름
    private BoardEntity boardEntity; // 반드시 부모 entity타입으로 작성해야함. 실제로 DB에는 id값만 저장됨


}

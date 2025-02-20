package com.example.board.Service;

import com.example.board.DTO.BoardDTO;
import com.example.board.Entity.BoardEntity;
import com.example.board.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직을 분리해야함

        if(boardDTO.getBoardFile().isEmpty()) {
            // 첨부 파일이 없는 경우
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        }
        else {
            // 첨부 파일이 있는 경우
            /* 1. DTO에 담긴 파일을 꺼냄
               2. 파일의 이름 가져옴
               3. 서버 저장용 이름을 만듦
               4. 저장 경로 설정
               5. 해당 경로에 파일 저장
               6. board_table에 해당 데이터 save 처리
               7. board_file_table에 해당 데이터 save 처리
             */

            MultipartFile boardFile = boardDTO.getBoardFile(); // 1번 과정
            String originalFileName = boardFile.getOriginalFilename(); // 2번 과정
            // 3번 과정에서 System.currentTimeMillis() 는 UUID로 대체 가능
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName; // 3번 과정
            String savePath = "C:/springboot_img/" + storedFileName; // 4번 과정
            boardFile.transferTo(new File(savePath)); // 5번 과정
        }
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }

    @Transactional // 메소드를 만들어서 사용하는 경우 이 애너테이션을 붙이지 않으면 에러 발생
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();

            return BoardDTO.toBoardDTO(boardEntity);
        }
        else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);

        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // list처럼 1페이지는 Request 기준으로 0부터 시작
        int pageLimit = 3;

        // 한 페이지에 3개씩 보여주고 id를 기준으로 내림차순 정렬
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        return boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
    }
}

package org.example.board.board.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardEntity;
import org.example.board.board.db.BoardRepository;
import org.example.board.board.model.BoardDto;
import org.example.board.board.model.BoardRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    public BoardDto create(BoardRequest boardRequest){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        var saveEntity = boardRepository.save(entity);
        return boardConverter.ToDto(saveEntity);
    }

    public BoardDto view(Long id) {
        var Entity = boardRepository.findById(id).get();
        return boardConverter.ToDto(Entity);
    }

    public BoardDto findByAll(){
        var Entity = boardRepository.findAll();
        return boardConverter.ToDto(Entity.get(0));
    }
}

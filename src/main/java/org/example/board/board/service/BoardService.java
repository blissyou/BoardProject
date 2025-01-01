package org.example.board.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardEntity;
import org.example.board.board.db.BoardRepository;
import org.example.board.board.model.BoardRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity create(BoardRequest boardRequest){
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        return boardRepository.save(entity);
    }
}

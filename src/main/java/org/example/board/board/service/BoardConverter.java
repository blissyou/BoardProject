package org.example.board.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardEntity;
import org.example.board.board.model.BoardDto;
import org.example.board.post.model.PostDto;
import org.example.board.post.service.PostConverter;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardConverter {

    private final PostConverter postConverter;

    public BoardDto ToDto(BoardEntity boardEntity){
        var postList = boardEntity.getPostlist()
                .stream()
                .map(postConverter::ToDto)
                .collect(Collectors.toList());


        return BoardDto.builder()
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postlist(postList)
                .build()
                ;
    }
}

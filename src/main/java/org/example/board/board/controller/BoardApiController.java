package org.example.board.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardEntity;
import org.example.board.board.model.BoardRequest;
import org.example.board.board.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardEntity create(
            @Valid
            @RequestBody BoardRequest boardrequest
    ){
        return boardService.create(boardrequest);
    }
}

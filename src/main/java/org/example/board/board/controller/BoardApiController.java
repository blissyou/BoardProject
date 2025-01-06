package org.example.board.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardEntity;
import org.example.board.board.model.BoardDto;
import org.example.board.board.model.BoardRequest;
import org.example.board.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("")
    public BoardDto create(
            @Valid
            @RequestBody BoardRequest boardrequest
    ){
        return boardService.create(boardrequest);
    }
    @GetMapping("/all")
    public List<BoardDto> findByAll(){
        return boardService.findByAll();
    }



    @GetMapping("/id/{id}")
    public BoardDto view(
            @PathVariable Long id
    ){
        return boardService.view(id);
    }
}

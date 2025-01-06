package org.example.board.reply.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.reply.db.ReplyEntity;
import org.example.board.reply.db.ReplyRepository;
import org.example.board.reply.model.ReplyDto;
import org.example.board.reply.model.ReplyRequest;
import org.example.board.reply.service.ReplyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final ReplyRepository replyRepository;

    @PostMapping("")
    public ReplyDto create(
            @Valid
            @RequestBody ReplyRequest replyRequest
    ){
        return replyService.create(replyRequest);
    }

    //fixme: API 설계 다시해야함
    @GetMapping("/id/{id}")
    public List<ReplyDto> view(long id){
        return replyService.findAllByPostId(id);
    }

}

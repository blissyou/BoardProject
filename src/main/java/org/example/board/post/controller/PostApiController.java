package org.example.board.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.post.db.PostEntity;
import org.example.board.post.model.PostRequest;
import org.example.board.post.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("")
    public PostEntity create(
            @Valid
            @RequestBody PostRequest postrequest
            ){
        return postService.create(postrequest);
    }
}

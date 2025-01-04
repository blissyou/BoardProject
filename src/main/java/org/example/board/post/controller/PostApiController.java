package org.example.board.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.post.common.Api;
import org.example.board.post.common.Pagination;
import org.example.board.post.db.PostEntity;
import org.example.board.post.model.PostRequest;
import org.example.board.post.model.PostViewRequest;
import org.example.board.post.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/id/{id}")
    public PostEntity view(
            @Valid
        @RequestBody PostViewRequest postViewRequest
    ){
        return postService.view(postViewRequest);
    }

    @GetMapping("/all")
    public Api<List<PostEntity>> list(
            @PageableDefault(page= 0,size = 10,sort= "id",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return postService.findAll(pageable);
    }

    @PostMapping("/delete")
    public void delete(
            @Valid
            @RequestBody PostViewRequest postViewRequest
    ){
        postService.delete(postViewRequest);
    }


}

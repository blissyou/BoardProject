package org.example.board.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.post.common.Api;
import org.example.board.post.model.PostDto;
import org.example.board.post.model.PostRequest;
import org.example.board.post.model.PostViewRequest;
import org.example.board.post.model.SearchCondition;
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
    public PostDto create(
            @Valid
            @RequestBody PostRequest postrequest
            ){
        return postService.create(postrequest);
    }


    @GetMapping("/id/{id}")
    public Api<List<PostDto>> view(
            @PageableDefault(page= 0,size = 10,sort= "id",direction = Sort.Direction.DESC)
            Pageable pageable,
            @Valid
            @PathVariable long id,
            SearchCondition searchCondition
    ){

        return postService.findAllByBoardIdAndStatusOrderByIdDesc(id,"REGISTERED",pageable,searchCondition);
    }

    @GetMapping("/all")
    public Api<List<PostDto>> list(
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

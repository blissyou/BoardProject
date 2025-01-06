package org.example.board.post.service;

import lombok.RequiredArgsConstructor;
import org.example.board.board.db.BoardRepository;
import org.example.board.board.service.BoardConverter;
import org.example.board.post.common.Api;
import org.example.board.post.common.Pagination;
import org.example.board.post.db.PostEntity;
import org.example.board.post.db.PostRepository;
import org.example.board.post.model.PostDto;
import org.example.board.post.model.PostRequest;
import org.example.board.post.model.PostViewRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final PostConverter postConverter;

    public PostDto create(
        PostRequest postRequest
    ){
        var boardEntity = boardRepository.findById(postRequest.getBoardId()).get();

        var entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        var saveEntity = postRepository.save(entity);
        return postConverter.ToDto(saveEntity);
    }

    /**
     * 1. 게시글이 있는가?
     * 2. 비밀번호가 맞는가
     */
    public PostDto view(PostViewRequest postViewRequest) {

        var Entity=  postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map(it -> {
                    //entity 존제하는지
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    return it;

                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다: " + postViewRequest.getPostId());
                        }
                );
        return postConverter.ToDto(Entity);

    }

    public Api<List<PostEntity>> findAll(Pageable pageable) {
        var list =postRepository.findAll(pageable);

        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElement(list.getNumberOfElements())
                .totalElement(list.getTotalElements())
                .totalPages(list.getTotalPages())
                .build()
                ;

        var response = Api.<List<PostEntity>>builder()
                .body(list.toList())
                .pagination(pagination)
                .build();
        return response;
    }

    public void delete( PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it -> {
                    //entity 존제하는지
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        var format = "패스워드가 맞지 않습니다 %s vs %s";
                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;

                }).orElseThrow(
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다: " + postViewRequest.getPostId());
                        }
                );
    }
}

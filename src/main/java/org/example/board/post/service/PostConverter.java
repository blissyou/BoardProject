package org.example.board.post.service;
import org.example.board.post.db.PostEntity;
import org.example.board.post.model.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {

    public PostDto ToDto(PostEntity postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .boardId(postEntity.getBoard().getId())
                .userName(postEntity.getUserName())
                .status(postEntity.getStatus())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .email(postEntity.getEmail())
                .postedAt(postEntity.getPostedAt())
                .password(postEntity.getPassword())
                .build();

    }
}

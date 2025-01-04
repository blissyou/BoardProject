package org.example.board.reply.service;

import lombok.RequiredArgsConstructor;
import org.example.board.post.db.PostEntity;
import org.example.board.post.db.PostRepository;
import org.example.board.reply.db.ReplyEntity;
import org.example.board.reply.db.ReplyRepository;
import org.example.board.reply.model.ReplyRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(ReplyRequest replyRequest){
        var optionalpostEntity = postRepository.findById(replyRequest.getPostId());

        if (optionalpostEntity.isEmpty()){
            throw new RuntimeException("게시물이 존제하지 않습니다"+replyRequest.getPostId());
        }
        var entity =ReplyEntity.builder()
                .post(optionalpostEntity.get())
                .userName(replyRequest.getUserName())
                .title(replyRequest.getTitle())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"REGISTERED");
    }
}

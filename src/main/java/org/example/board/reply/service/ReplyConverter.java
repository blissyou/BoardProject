package org.example.board.reply.service;
import org.example.board.reply.db.ReplyEntity;
import org.example.board.reply.model.ReplyDto;
import org.springframework.stereotype.Service;

@Service
public class ReplyConverter {
    public ReplyDto ToDto(ReplyEntity replyEntity){
        return ReplyDto.builder()
                .id(replyEntity.getId())
                .title(replyEntity.getTitle())
                .post(replyEntity.getPost())
                .status(replyEntity.getStatus())
                .content(replyEntity.getContent())
                .userName(replyEntity.getUserName())
                .repliedAt(replyEntity.getRepliedAt())
                .password(replyEntity.getPassword())
                .build();

    }
}

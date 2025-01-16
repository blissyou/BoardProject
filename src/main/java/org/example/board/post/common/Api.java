package org.example.board.post.common;

import lombok.*;
import org.example.board.post.db.PostEntity;
import org.example.board.post.model.SearchCondition;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Api<T> {
    private T body;
    private Pagination pagination;
    private Page<PostEntity> searchCondition;
}

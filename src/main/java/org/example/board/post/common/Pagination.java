package org.example.board.post.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Pagination {
    private Integer page;
    private Integer size;
    private Integer currentElement;
    private Integer totalPages;
    private Long totalElement;
}

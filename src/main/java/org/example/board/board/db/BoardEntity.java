package org.example.board.board.db;

import jakarta.persistence.*;
import lombok.*;
import org.example.board.post.db.PostEntity;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String boardName;
    private String status;

    @OneToMany(mappedBy = "board")
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default
    @OrderBy("id desc")
    private List<PostEntity> postlist = List.of();

}

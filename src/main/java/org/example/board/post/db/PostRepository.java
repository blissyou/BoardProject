package org.example.board.post.db;

import org.example.board.post.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    Page<PostEntity> findAllByBoardIdAndStatusOrderByIdDesc(Long boardId, String status, Pageable pageable);
}
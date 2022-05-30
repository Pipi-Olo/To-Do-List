package com.item.domain.comment;

import com.item.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByItem(Item item);

    @Override
    Optional<Comment> findById(Long commentId);

    @Override
    List<Comment> findAll();

    @Override
    <S extends Comment> S save(S entity);

    @Override
    void delete(Comment comment);

    @Override
    void deleteById(Long commentId);

    @Override
    void deleteAll();
}

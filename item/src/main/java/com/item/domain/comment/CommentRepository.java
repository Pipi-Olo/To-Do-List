package com.item.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    Optional<Comment> findById(Long aLong);

    @Override
    List<Comment> findAll();

    @Override
    <S extends Comment> S save(S entity);
    
    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();
}

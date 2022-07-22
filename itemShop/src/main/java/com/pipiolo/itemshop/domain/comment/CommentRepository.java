package com.pipiolo.itemshop.domain.comment;

import com.pipiolo.itemshop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByItem(Item item);
}

package com.item.web.comment;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void save(Long itemId, CommentSaveForm saveForm, String username) {
        Item findItem = itemRepository.findById(itemId).get();

        Comment comment = saveForm.toEntity(username, findItem);
        findItem.addComment(comment);

        commentRepository.save(comment);
    }
}

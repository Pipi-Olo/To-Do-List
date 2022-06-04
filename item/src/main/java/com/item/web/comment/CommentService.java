package com.item.web.comment;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment save(Long itemId, CommentSaveForm saveForm, String username) {
        Item findItem = itemRepository.findById(itemId).get();

        Comment comment = saveForm.toEntity(username, findItem);
        findItem.addComment(comment);

        return commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId).get();
    }

    @Transactional(readOnly = true)
    public List<Comment> findByItem(Item item) {
        return commentRepository.findByItem(item);
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional
    public void update(Long commentId, CommentUpdateForm updateForm) {
        Comment findComment = commentRepository.findById(commentId).get();
        findComment.update(updateForm.toEntity());
    }
}

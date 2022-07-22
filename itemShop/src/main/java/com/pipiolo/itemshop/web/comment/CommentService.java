package com.pipiolo.itemshop.web.comment;

import com.pipiolo.itemshop.domain.comment.Comment;
import com.pipiolo.itemshop.domain.comment.CommentRepository;
import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.item.ItemRepository;
import com.pipiolo.itemshop.domain.user.User;
import com.pipiolo.itemshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void save(CommentSaveForm saveForm, String username, Long itemId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find username=" + username));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));

        Comment comment = new Comment(saveForm.getMessage(), item, user);

        commentRepository.save(comment);
    }

    public List<CommentResponse> findAll(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));

        return commentRepository.findByItem(item).stream()
                .map(CommentResponse::new)
                .collect(toList());
    }
}

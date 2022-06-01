package com.item.web.item;

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
public class ItemService {

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Item save(ItemSaveForm saveForm) {
        return itemRepository.save(saveForm.toEntity());
    }

    @Transactional
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public void update(Long itemId, ItemUpdateForm updateForm) {
        Item findItem = itemRepository.findById(itemId).get();
        findItem.update(updateForm.toEntity());
    }

    @Transactional
    public void delete(Long itemId) {
        Item findItem = itemRepository.findById(itemId).get();
        List<Comment> findComments = commentRepository.findByItem(findItem);

        for (Comment comment : findComments) {
            commentRepository.delete(comment);
        }
        itemRepository.delete(findItem);
    }

    @Transactional
    public void clearStore() {
        itemRepository.deleteAll();
    }
}

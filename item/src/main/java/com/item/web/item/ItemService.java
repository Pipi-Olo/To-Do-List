package com.item.web.item;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
import com.item.web.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final CommentService commentService;

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Item save(String username, ItemSaveForm saveForm) {
        User seller = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Username=" + username));

        Item item = itemRepository.save(saveForm.toEntity(seller));
        seller.addItem(item);

        return item;
    }

    @Transactional(readOnly = true)
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Item> findAll(ItemSearchCond searchCond) {
        return itemRepository.findItemsBySearchParams(searchCond);
    }

    @Transactional
    public void update(Long itemId, ItemUpdateForm updateForm) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));
        findItem.update(updateForm.toEntity());
    }

    @Transactional
    public void delete(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));
        List<Comment> comments = findItem.getComments();

        for (Comment comment : comments) {
            commentService.delete(comment.getId());
        }

        User seller = findItem.getSeller();
        seller.getItems().remove(findItem);

        itemRepository.delete(findItem);
    }

    @Transactional
    public void clearStore() {
        itemRepository.deleteAll();
    }
}

package com.item.web.comment;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ItemRepository itemRepository;

    public void save(Long itemId, CommentSaveForm saveForm, String username) {
        Item findItem = itemRepository.findById(itemId);
        findItem.addComment(saveForm.toEntity(username));
    }
}

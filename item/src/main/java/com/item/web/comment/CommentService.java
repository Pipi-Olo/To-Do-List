package com.item.web.comment;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ItemRepository itemRepository;

    public void save(Long itemId, CommentSaveForm saveForm, Principal principal) {
        Item findItem = itemRepository.findById(itemId);
        findItem.addComment(saveForm.toEntity(principal.getName()));
    }
}

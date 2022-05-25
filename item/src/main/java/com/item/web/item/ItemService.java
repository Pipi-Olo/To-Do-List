package com.item.web.item;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(ItemSaveForm saveForm) {
        return itemRepository.save(saveForm);
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void update(Long itemId, ItemUpdateForm updateForm) {
        itemRepository.update(itemId, updateForm);
    }

    public void clearStore() {
        itemRepository.clearStore();
    }
}

package com.item.web.item;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepositoryV2 itemRepository;

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
    public void clearStore() {
        itemRepository.deleteAll();
    }
}

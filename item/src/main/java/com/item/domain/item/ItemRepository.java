package com.item.domain.item;

import com.item.web.item.ItemSaveForm;
import com.item.web.item.ItemUpdateForm;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(ItemSaveForm saveForm) {
        Item item = saveForm.toEntity();
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, ItemUpdateForm updateParam) {
        Item findItem = findById(itemId);
        findItem.update(updateParam.toEntity());
    }

    public void clearStore() {
        store.clear();
    }

}


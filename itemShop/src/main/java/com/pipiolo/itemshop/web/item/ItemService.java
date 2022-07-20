package com.pipiolo.itemshop.web.item;

import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void save(ItemSaveForm saveForm) {
        itemRepository.save(saveForm.toEntity());
    }

    public ItemResponse findById(Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));

        return new ItemResponse(findItem);
    }

    public List<ItemResponse> findAll(ItemSearchCond searchCond) {
        return itemRepository.findItemsBySearchParams(searchCond).stream()
                .map(ItemResponse::new)
                .collect(toList());
    }

    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponse::new)
                .collect(toList());
    }
}

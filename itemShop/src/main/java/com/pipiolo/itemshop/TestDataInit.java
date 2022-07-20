package com.pipiolo.itemshop;

import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.item.ItemRepository;
import com.pipiolo.itemshop.domain.order.OrderRepository;
import com.pipiolo.itemshop.domain.user.User;
import com.pipiolo.itemshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        saveUser(new User("userA", "test"));
        saveUser(new User("userB", "test"));

        saveItem(new Item("itemA", 10000, 10));
        saveItem(new Item("itemB", 20000, 20));
    }

    private void saveUser(User user) {
        userRepository.save(user);
    }

    private void saveItem(Item item) {
        itemRepository.save(item);
    }
}

package com.pipiolo.itemshop;

import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.item.ItemRepository;
import com.pipiolo.itemshop.domain.user.User;
import com.pipiolo.itemshop.domain.user.UserRepository;
import com.pipiolo.itemshop.web.item.ItemSaveForm;
import com.pipiolo.itemshop.web.item.ItemService;
import com.pipiolo.itemshop.web.user.UserSaveForm;
import com.pipiolo.itemshop.web.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class TestDataInit {

    private final UserService userService;
    private final ItemService itemService;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        saveUser(new UserSaveForm("userA", "test"));
        saveUser(new UserSaveForm("userB", "test"));

        saveItem(new ItemSaveForm("itemA", 10000, 10));
        saveItem(new ItemSaveForm("itemB", 20000, 20));
    }

    private void saveUser(UserSaveForm saveForm) {
        userService.save(saveForm);
    }

    private void saveItem(ItemSaveForm saveForm) {
        itemService.save(saveForm);
    }
}

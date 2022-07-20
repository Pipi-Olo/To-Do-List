package com.pipiolo.itemshop;

import com.pipiolo.itemshop.web.item.ItemService;
import com.pipiolo.itemshop.web.user.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ItemShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemShopApplication.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit(
            UserService userService,
            ItemService itemService
    ) {
        return new TestDataInit(userService, itemService);
    }
}

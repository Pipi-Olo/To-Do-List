package com.pipiolo.itemshop;

import com.pipiolo.itemshop.domain.item.ItemRepository;
import com.pipiolo.itemshop.domain.order.OrderRepository;
import com.pipiolo.itemshop.domain.user.UserRepository;
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
            UserRepository userRepository,
            ItemRepository itemRepository
    ) {
        return new TestDataInit(userRepository, itemRepository);
    }
}

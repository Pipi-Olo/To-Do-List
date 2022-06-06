package com.item;

import com.item.domain.comment.CommentRepository;
import com.item.domain.item.ItemRepository;
import com.item.domain.order.OrderRepository;
import com.item.domain.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit(UserRepository userRepository,
                                     ItemRepository itemRepository,
                                     CommentRepository commentRepository,
                                     OrderRepository orderRepository
    ) {
        return new TestDataInit(userRepository, itemRepository, commentRepository, orderRepository);
    }
}

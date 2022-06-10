package com.item;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initDate() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User userA = userRepository.save(new User("aaa", encoder.encode("test")));
        User userB = userRepository.save(new User("bbb", encoder.encode("test")));
        User userC = userRepository.save(new User("ccc", encoder.encode("test")));

        //

        Item itemA1 = new Item("itemA1", 10000, 10, userA);
        Item itemA2 = new Item("itemA2", 20000, 20, userA);
        Item itemA3 = new Item("itemA3", 30000, 30, userA);
        Item itemB1 = new Item("itemB1", 10000, 10, userB);
        Item itemB2 = new Item("itemB2", 20000, 20, userB);
        Item itemC1 = new Item("itemC1", 10000, 10, userC);
        Item itemC2 = new Item("itemC2", 20000, 20, userC);

        itemRepository.save(itemA1);
        itemRepository.save(itemA2);
        itemRepository.save(itemA3);
        itemRepository.save(itemB1);
        itemRepository.save(itemB2);
        itemRepository.save(itemC1);
        itemRepository.save(itemC2);

        //

        commentRepository.save(new Comment("test message A11", userB, itemA1));
        commentRepository.save(new Comment("test message A12", userC, itemA1));
        commentRepository.save(new Comment("test message A21", userB, itemA2));
        commentRepository.save(new Comment("test message A22", userC, itemA2));
        commentRepository.save(new Comment("test message A31", userB, itemA3));
        commentRepository.save(new Comment("test message A32", userC, itemA3));
        commentRepository.save(new Comment("test message B11", userA, itemB1));
        commentRepository.save(new Comment("test message B12", userC, itemB1));
        commentRepository.save(new Comment("test message B21", userA, itemB2));
        commentRepository.save(new Comment("test message B22", userC, itemB2));
        commentRepository.save(new Comment("test message C11", userA, itemC1));
        commentRepository.save(new Comment("test message C12", userB, itemC1));
        commentRepository.save(new Comment("test message C21", userA, itemC2));
        commentRepository.save(new Comment("test message C22", userB, itemC2));

        itemRepository.save(itemA1);
        itemRepository.save(itemA2);
        itemRepository.save(itemA3);
        itemRepository.save(itemB1);
        itemRepository.save(itemB2);
        itemRepository.save(itemC1);
        itemRepository.save(itemC2);

        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);
    }

}

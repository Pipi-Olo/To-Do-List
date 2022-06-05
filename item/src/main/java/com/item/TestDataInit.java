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

import static com.item.domain.user.Role.*;

@RequiredArgsConstructor
public class TestDataInit {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initDate() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User userA = userRepository.save(new User("test", encoder.encode("test")));
        User userB = userRepository.save(new User("test2", encoder.encode("test2")));

        Item itemA = new Item("itemA", 10000, 10, userA);
        Item itemB = new Item("itemB", 20000, 20, userB);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        userA.addItem(itemA);
        userB.addItem(itemB);

        Comment commentA1 = commentRepository.save(new Comment("test message A1", userA, itemA));
        Comment commentA2 = commentRepository.save(new Comment("test message A2", userB, itemA));
        Comment commentB1 = commentRepository.save(new Comment("test message B1", userA, itemB));
        Comment commentB2 = commentRepository.save(new Comment("test message B2", userB, itemB));

        itemA.addComment(commentA1);
        itemA.addComment(commentA2);
        itemB.addComment(commentB1);
        itemB.addComment(commentB2);

        userA.addComment(commentA1);
        userA.addComment(commentB1);
        userB.addComment(commentA2);
        userB.addComment(commentB2);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        userRepository.save(userA);
        userRepository.save(userB);
    }

}

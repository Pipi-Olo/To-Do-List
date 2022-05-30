package com.item;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
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

        userRepository.save(new User("test", encoder.encode("test")));
        userRepository.save(new User("test2", encoder.encode("test2")));


        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        itemRepository.save(itemA);
        itemRepository.save(itemB);


        Comment commentA1 = new Comment("test message A1", "test", itemA);
        Comment commentA2 = new Comment("test message A2", "test2", itemA);
        Comment commentB1 = new Comment("test message B1", "test", itemB);
        Comment commentB2 = new Comment("test message B2", "test2", itemB);

        itemA.addComment(commentA1);
        itemA.addComment(commentA2);
        itemB.addComment(commentB1);
        itemB.addComment(commentB2);

        commentRepository.save(commentA1);
        commentRepository.save(commentA2);
        commentRepository.save(commentB1);
        commentRepository.save(commentB2);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }

}

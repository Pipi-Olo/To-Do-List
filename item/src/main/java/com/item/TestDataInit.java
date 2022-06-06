package com.item;

import com.item.domain.comment.Comment;
import com.item.domain.comment.CommentRepository;
import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.order.Order;
import com.item.domain.order.OrderRepository;
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
    private final OrderRepository orderRepository;

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

        userA.addItem(itemA1);
        userA.addItem(itemA2);
        userA.addItem(itemA3);
        userB.addItem(itemB1);
        userB.addItem(itemB2);
        userC.addItem(itemC1);
        userC.addItem(itemC2);

        //

        Comment commentA11 = commentRepository.save(new Comment("test message A11", userB, itemA1));
        Comment commentA12 = commentRepository.save(new Comment("test message A12", userC, itemA1));
        Comment commentA21 = commentRepository.save(new Comment("test message A21", userB, itemA2));
        Comment commentA22 = commentRepository.save(new Comment("test message A22", userC, itemA2));
        Comment commentA31 = commentRepository.save(new Comment("test message A31", userB, itemA3));
        Comment commentA32 = commentRepository.save(new Comment("test message A32", userC, itemA3));
        Comment commentB11 = commentRepository.save(new Comment("test message B11", userA, itemB1));
        Comment commentB12 = commentRepository.save(new Comment("test message B12", userC, itemB1));
        Comment commentB21 = commentRepository.save(new Comment("test message B21", userA, itemB2));
        Comment commentB22 = commentRepository.save(new Comment("test message B22", userC, itemB2));
        Comment commentC11 = commentRepository.save(new Comment("test message C11", userA, itemC1));
        Comment commentC12 = commentRepository.save(new Comment("test message C12", userB, itemC1));
        Comment commentC21 = commentRepository.save(new Comment("test message C21", userA, itemC2));
        Comment commentC22 = commentRepository.save(new Comment("test message C22", userB, itemC2));

        itemA1.addComment(commentA11);
        itemA1.addComment(commentA12);
        itemA2.addComment(commentA21);
        itemA2.addComment(commentA22);
        itemA3.addComment(commentA31);
        itemA3.addComment(commentA32);
        itemB1.addComment(commentB11);
        itemB1.addComment(commentB12);
        itemB2.addComment(commentB21);
        itemB2.addComment(commentB22);
        itemC1.addComment(commentC11);
        itemC1.addComment(commentC12);
        itemC2.addComment(commentC21);
        itemC2.addComment(commentC22);

        userA.addComment(commentB11);
        userA.addComment(commentB21);
        userA.addComment(commentC11);
        userA.addComment(commentC21);
        userB.addComment(commentA11);
        userB.addComment(commentA21);
        userB.addComment(commentA31);
        userB.addComment(commentC12);
        userB.addComment(commentC22);
        userC.addComment(commentA12);
        userC.addComment(commentA22);
        userC.addComment(commentA32);
        userC.addComment(commentB12);
        userC.addComment(commentB22);

        //

//        Order order = orderRepository.save(new Order(itemA1, userB, 3));
//        itemA1.addOrder(order);
//        userB.addPurchase(order);

        //

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

package com.pipiolo.itemshop;

import com.pipiolo.itemshop.domain.delivery.Address;
import com.pipiolo.itemshop.domain.delivery.Delivery;
import com.pipiolo.itemshop.domain.delivery.DeliveryStatus;
import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.item.ItemRepository;
import com.pipiolo.itemshop.domain.order.Order;
import com.pipiolo.itemshop.domain.order.OrderRepository;
import com.pipiolo.itemshop.domain.orderItem.OrderItem;
import com.pipiolo.itemshop.domain.user.User;
import com.pipiolo.itemshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public class TestDataInit {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        User userA = userRepository.save(new User("userA", encoder.encode("test")));

        Item itemA = itemRepository.save(new Item("itemA", 10000, 100));
        Item itemB = itemRepository.save(new Item("itemB", 20000, 200));
        Item itemC = itemRepository.save(new Item("itemC", 30000, 300));

        OrderItem orderItemA = new OrderItem(itemA, 10);
        OrderItem orderItemB = new OrderItem(itemB, 10);
        OrderItem orderItemC = new OrderItem(itemC, 10);

        Delivery delivery = new Delivery(new Address("test", "test", "123456"), DeliveryStatus.READY);

        orderRepository.save(new Order(userA, delivery, List.of(orderItemA, orderItemB, orderItemC)));
    }

}

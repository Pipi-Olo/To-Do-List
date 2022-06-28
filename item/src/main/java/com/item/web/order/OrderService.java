package com.item.web.order;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.order.Order;
import com.item.domain.order.OrderRepository;
import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Order save(Long itemId, String username, OrderSaveForm saveForm) {
        Item findItem = itemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + itemId));

        User seller = findItem.getSeller();
        User buyer = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Username=" + username));
        Order order = saveForm.toEntity(findItem, seller, buyer);

        findItem.addOrder(order);

        return orderRepository.save(order);
    }
}

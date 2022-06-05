package com.item.web.order;

import com.item.domain.item.Item;
import com.item.domain.order.Order;
import com.item.domain.order.OrderRepository;
import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order save(Item findItem, String username, OrderSaveForm saveForm) {
        User seller = findItem.getSeller();
        User buyer = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Username=" + username));
        Order order = saveForm.toEntity(findItem, buyer);

        seller.addSale(order);
        buyer.addPurchase(order);
        findItem.addOrder(order);

        return orderRepository.save(order);
    }
}

package com.pipiolo.itemshop.web.order;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;


    // TODO: itemID 를 List 로 받아서 orderItem 을 List 로 생성하는 로직으로 변경 필요 + addOrderForm, orderSaveForm 도 같이 변경 필요
    @Transactional
    public Long save(OrderSaveForm saveForm, String username) {
        User buyer = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find username=" + username));

        Item item = itemRepository.findById(saveForm.getItemId())
                .orElseThrow(() -> new NoSuchElementException("Can Not Find itemId=" + saveForm.getItemId()));

        Delivery delivery = new Delivery(saveForm.getAddress(), DeliveryStatus.READY);
        OrderItem orderItem = new OrderItem(item, saveForm.getQuantity());

        Order order = new Order(buyer, delivery);
        order.addOrderItem(orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public OrderDetailResponse findById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find orderId=" + orderId));

        return new OrderDetailResponse(order);
    }

    public List<OrderResponse> findAllByUsername(String username) {
        User buyer = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find username=" + username));

        return orderRepository.findByBuyer(buyer).stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
    }
}

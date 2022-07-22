package com.pipiolo.itemshop.domain.order;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.domain.delivery.Delivery;
import com.pipiolo.itemshop.domain.orderItem.OrderItem;
import com.pipiolo.itemshop.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor
@Entity @Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems = new ArrayList<>();

    public Order(User buyer, Delivery delivery) {
        this.buyer = buyer;
        this.delivery = delivery;
    }

    public Order(User buyer, Delivery delivery, List<OrderItem> orderItems) {
        this.buyer = buyer;
        this.delivery = delivery;
        for (OrderItem orderItem : orderItems) {
            addOrderItem(orderItem);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}

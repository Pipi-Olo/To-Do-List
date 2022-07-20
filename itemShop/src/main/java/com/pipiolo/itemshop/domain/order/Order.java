package com.pipiolo.itemshop.domain.order;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.domain.delivery.Delivery;
import com.pipiolo.itemshop.domain.orderItem.OrderItem;
import com.pipiolo.itemshop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "orders") @EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User buyer;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
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

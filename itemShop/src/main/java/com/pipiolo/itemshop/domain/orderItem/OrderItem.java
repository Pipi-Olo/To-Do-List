package com.pipiolo.itemshop.domain.orderItem;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity @EntityListeners(AuditingEntityListener.class)
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private int quantity;

    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;

        this.item.removeStock(quantity);
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

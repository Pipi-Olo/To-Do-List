package com.item.domain.order;

import com.item.domain.item.Item;
import com.item.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity @Table(name = "orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Item item;

    @OneToOne
    private User user;

    private Integer quantity;


    public Order(Item item, User user, Integer quantity) {
        this.item = item;
        this.user = user;
        this.quantity = quantity;
    }
}

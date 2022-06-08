package com.item.domain.item;

import com.item.domain.order.Order;
import com.item.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String itemName;

    @Column
    private Integer price;

    @Column
    private Integer quantity;

    @ManyToOne
    private User seller;

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String itemName, Integer price, Integer quantity, User seller) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.seller = seller;
    }

    public void addOrder(Order order) {
        this.quantity -= order.getQuantity();
    }

    public void update(Item update) {
        this.itemName = update.getItemName();
        this.price = update.getPrice();
        this.quantity = update.getQuantity();
    }
}

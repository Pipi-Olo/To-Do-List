package com.item.domain.item;

import com.item.domain.comment.Comment;
import com.item.domain.order.Order;
import com.item.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
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

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    private List<Order> orders = new ArrayList<>();

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

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addOrder(Order order) {
        this.quantity -= order.getQuantity();
        orders.add(order);
    }

    public void update(Item update) {
        this.itemName = update.getItemName();
        this.price = update.getPrice();
        this.quantity = update.getQuantity();
    }
}

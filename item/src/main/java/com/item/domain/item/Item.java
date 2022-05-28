package com.item.domain.item;

import com.item.domain.comment.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    private List<Comment> comments;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.comments = new ArrayList<>();
    }

    public Item(String itemName, Integer price, Integer quantity, List<Comment> comments) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.comments = comments;
    }

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void update(Item update) {
        this.itemName = update.getItemName();
        this.price = update.getPrice();
        this.quantity = update.getQuantity();
    }
}

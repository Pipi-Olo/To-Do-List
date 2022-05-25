package com.item.domain.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(Item update) {
        this.itemName = update.getItemName();
        this.price = update.getPrice();
        this.quantity = update.getQuantity();
    }
}

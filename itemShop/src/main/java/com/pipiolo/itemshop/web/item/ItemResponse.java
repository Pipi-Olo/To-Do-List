package com.pipiolo.itemshop.web.item;

import com.pipiolo.itemshop.domain.item.Item;
import lombok.Data;

@Data
public class ItemResponse {

    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
    }
}

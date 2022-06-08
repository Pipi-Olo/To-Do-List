package com.item.web.item;

import com.item.domain.comment.Comment;
import com.item.domain.item.Item;
import com.item.domain.user.User;
import lombok.Data;

import java.util.List;

@Data
public class ItemResponse {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private User seller;
    private List<Comment> comments;

    public ItemResponse(Item item, List<Comment> comments) {
        this.id = item.getId();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.seller = item.getSeller();
        this.comments = comments;
    }

    public Item toEntity() {
        return new Item(id, itemName, price, quantity, seller);
    }
}

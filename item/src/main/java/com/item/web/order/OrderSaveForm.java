package com.item.web.order;

import com.item.domain.item.Item;
import com.item.domain.order.Order;
import com.item.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class OrderSaveForm {

    @Positive
    private Integer quantity;

    public Order toEntity(Item item, User buyer) {
        return new Order(item, buyer, quantity);
    }
}

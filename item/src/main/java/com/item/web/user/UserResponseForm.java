package com.item.web.user;

import com.item.domain.item.Item;
import com.item.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseForm {

    private String username;
    private List<Item> items;
    private List<Order> orders;

    public UserResponseForm(String username, List<Item> items, List<Order> orders) {
        this.username = username;
        this.items = items;
        this.orders = orders;
    }
}

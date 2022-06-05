package com.item.web.user;

import com.item.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseForm {

    private String username;
    private List<Order> orders;

    public UserResponseForm(String username, List<Order> orders) {
        this.username = username;
        this.orders = orders;
    }
}

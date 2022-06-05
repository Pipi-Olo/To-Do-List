package com.item.web.user;

import com.item.domain.item.Item;
import com.item.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseForm {

    private String username;
    private List<Item> items;
    private List<Order> purchases;
    private List<Order> sales;
    private Integer revenue;

    public UserResponseForm(String username, List<Item> items, List<Order> purchases, List<Order> sales) {
        this.username = username;
        this.items = items;
        this.purchases = purchases;
        this.sales = sales;

        int sum = 0;
        for (Order purchase : purchases) {
            sum -= purchase.getQuantity() * purchase.getItem().getPrice();
        }
        for (Order sale : sales) {
            sum += sale.getQuantity() * sale.getItem().getPrice();
        }

        revenue = sum;
    }
}

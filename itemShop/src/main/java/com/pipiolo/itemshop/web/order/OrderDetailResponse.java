package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.domain.order.Order;
import com.pipiolo.itemshop.domain.orderItem.OrderItem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDetailResponse {

    private String username;
    private String address;
    private List<OrderItemResponse> orderItems = new ArrayList<>();

    public OrderDetailResponse(Order order) {
        this.username = order.getBuyer().getUsername();
        this.address = order.getDelivery().getAddress().toString();

        List<OrderItem> orderItemList = order.getOrderItems();
        for (OrderItem orderItem : orderItemList) {
            orderItems.add(new OrderItemResponse(orderItem));
        }
    }

    @Data
    static class OrderItemResponse {

        private String itemName;
        private Integer price;
        private Integer quantity;

        public OrderItemResponse(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.price = orderItem.getItem().getPrice();
            this.quantity = orderItem.getQuantity();
        }
    }
}

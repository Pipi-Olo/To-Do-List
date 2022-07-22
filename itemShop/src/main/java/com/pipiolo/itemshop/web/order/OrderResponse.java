package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.domain.delivery.DeliveryStatus;
import com.pipiolo.itemshop.domain.order.Order;
import lombok.Data;

@Data
public class OrderResponse {

    private Long id;
    private String buyer;
    private DeliveryStatus deliveryStatus;
    private String address;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.buyer = order.getBuyer().getUsername();
        this.deliveryStatus = order.getDelivery().getStatus();
        this.address = order.getDelivery().getAddress().toString();
    }
}

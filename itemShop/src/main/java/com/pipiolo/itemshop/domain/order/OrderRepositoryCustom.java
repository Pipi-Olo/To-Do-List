package com.pipiolo.itemshop.domain.order;

public interface OrderRepositoryCustom {

    Order findOneById(Long orderId);
}

package com.pipiolo.itemshop.domain.order;

import com.pipiolo.itemshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface OrderRepository extends
        JpaRepository<Order, Long>,
        OrderRepositoryCustom,
        QuerydslPredicateExecutor<Order>,
        QuerydslBinderCustomizer<QOrder>
{
    List<Order> findByBuyer(User buyer);

    @Override
    default void customize(QuerydslBindings bindings, QOrder root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.buyer, root.delivery, root.orderItems);
    }
}

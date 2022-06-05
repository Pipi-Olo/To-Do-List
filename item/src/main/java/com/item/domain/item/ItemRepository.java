package com.item.domain.item;

import com.item.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface ItemRepository extends
        JpaRepository<Item, Long>,
        ItemRepositoryCustom,
        QuerydslPredicateExecutor<Item>,
        QuerydslBinderCustomizer<QItem>
{

    List<Item> findBySeller(User seller);

    @Override
    default void customize(QuerydslBindings bindings, QItem root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.itemName, root.price, root.quantity);
    }
}

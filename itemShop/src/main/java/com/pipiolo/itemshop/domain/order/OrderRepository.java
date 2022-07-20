package com.pipiolo.itemshop.domain.order;

import com.pipiolo.itemshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBuyer(User buyer);
}

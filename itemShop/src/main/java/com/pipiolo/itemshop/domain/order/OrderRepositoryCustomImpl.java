package com.pipiolo.itemshop.domain.order;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;

import static com.pipiolo.itemshop.domain.order.QOrder.*;

public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom{

    private final JPAQueryFactory query;

    public OrderRepositoryCustomImpl(EntityManager em) {
        super(Order.class);
        query = new JPAQueryFactory(em);
    }

    @Override
    public Order findOneById(Long orderId) {
        return query
                .selectDistinct(order)
                .from(order)
                .join(order.buyer)
                .fetchJoin()
                .join(order.delivery)
                .fetchJoin()
                .where(order.id.eq(orderId))
                .fetchOne();
    }
}

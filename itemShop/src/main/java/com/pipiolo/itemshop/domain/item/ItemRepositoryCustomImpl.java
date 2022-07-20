package com.pipiolo.itemshop.domain.item;

import com.pipiolo.itemshop.web.item.ItemSearchCond;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.pipiolo.itemshop.domain.item.QItem.*;

public class ItemRepositoryCustomImpl extends QuerydslRepositorySupport implements ItemRepositoryCustom {

    private final JPAQueryFactory query;

    public ItemRepositoryCustomImpl(EntityManager em) {
        super(Item.class);
        query = new JPAQueryFactory(em);
    }

    @Override
    public List<Item> findItemsBySearchParams(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        return query
                .select(item)
                .from(item)
                .where(likeItemName(itemName), loeMaxPrice(maxPrice))
                .fetch();
    }

    private Predicate likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.name.like("%" + itemName + "%");
        }
        return null;
    }

    private Predicate loeMaxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}

package com.item.domain.item;

import com.item.web.item.ItemSearchCond;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findItemsBySearchParams(ItemSearchCond cond);
}

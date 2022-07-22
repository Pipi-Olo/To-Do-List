package com.pipiolo.itemshop.domain.item;

import com.pipiolo.itemshop.web.item.ItemSearchCond;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> findItemsBySearchParams(ItemSearchCond cond);
}

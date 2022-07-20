package com.pipiolo.itemshop.web.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ItemSearchCond {

    private String itemName;
    private Integer maxPrice;
}

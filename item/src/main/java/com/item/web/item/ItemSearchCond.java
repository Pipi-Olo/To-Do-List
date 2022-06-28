package com.item.web.item;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemSearchCond {

    private String itemName;
    private Integer maxPrice;
}

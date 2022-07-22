package com.pipiolo.itemshop.web.item;

import com.pipiolo.itemshop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor
public class ItemSaveForm {

    @NotBlank
    private String name;

    @Range(min = 1000, max = 1000000)
    private Integer price;

    @Range(min = 0, max = 10000)
    private Integer quantity;

    public Item toEntity() {
        return new Item(name, price, quantity);
    }
}

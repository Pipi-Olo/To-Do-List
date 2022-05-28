package com.item.web.item;

import com.item.domain.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor
public class ItemSaveForm implements ItemRequestForm {

    @NotBlank
    private String itemName;

    @Range(min = 1000, max = 1000000)
    private Integer price;

    @Range(min = 0, max = 10000)
    private Integer quantity;

    public ItemSaveForm(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item toEntity() {
        return new Item(itemName, price, quantity);
    }
}

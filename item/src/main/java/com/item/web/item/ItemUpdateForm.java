package com.item.web.item;

import com.item.domain.item.Item;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ItemUpdateForm implements ItemRequestForm {

    @NotNull
    @PositiveOrZero
    private Long id;

    @NotBlank
    private String itemName;

    @Range(min = 1000, max = 1000000)
    private Integer price;

    @Range(min = 0, max = 10000)
    private Integer quantity;

    public ItemUpdateForm(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item toEntity() {
        return new Item(id, itemName, price, quantity);
    }
}

package com.item.web.item;

import com.item.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor @NoArgsConstructor
public class ItemSaveForm implements ItemRequestForm {

    @NotBlank
    private String itemName;

    @Range(min = 1000, max = 1000000)
    private Integer price;

    @Range(min = 0, max = 10000)
    private Integer quantity;

    public Item toEntity() {
        return new Item(itemName, price, quantity);
    }
}

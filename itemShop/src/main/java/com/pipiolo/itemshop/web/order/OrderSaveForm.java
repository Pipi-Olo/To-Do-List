package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.domain.delivery.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderSaveForm {

    private Long itemId;
    private Integer quantity;

    private String city;
    private String street;
    private String zipcode;

    public Address getAddress() {
        return new Address(city, street, zipcode);
    }
}

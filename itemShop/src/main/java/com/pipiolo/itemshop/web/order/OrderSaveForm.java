package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.domain.delivery.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderSaveForm {

    @Positive
    private Long itemId;

    @Positive(message = "수량은 0개 이상 숫자만 가능합니다.")
    private Integer quantity;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @Pattern(regexp = "[0-9]")
    private String zipcode;

    public Address getAddress() {
        return new Address(city, street, zipcode);
    }
}

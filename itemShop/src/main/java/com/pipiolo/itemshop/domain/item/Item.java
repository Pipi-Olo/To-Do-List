package com.pipiolo.itemshop.domain.item;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.web.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.pipiolo.itemshop.web.exception.ErrorCode.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Item extends BaseEntity {

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int rest = stockQuantity - quantity;
        if (rest < 0) {
            throw new GlobalException(BAD_REQUEST, "재고 수량이 부족합니다.");
        }
        this.stockQuantity = rest;
    }
}

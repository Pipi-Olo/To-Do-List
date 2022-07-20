package com.pipiolo.itemshop.domain.item;

import com.pipiolo.itemshop.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity @EntityListeners(AuditingEntityListener.class)
public class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public Item(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int rest = stockQuantity - quantity;
        if (rest < 0) {

        }
        this.stockQuantity = rest;
    }
}

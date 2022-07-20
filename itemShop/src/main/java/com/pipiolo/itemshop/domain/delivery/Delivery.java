package com.pipiolo.itemshop.domain.delivery;

import com.pipiolo.itemshop.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Delivery extends BaseEntity {

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}

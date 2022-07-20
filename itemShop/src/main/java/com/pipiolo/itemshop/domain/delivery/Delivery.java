package com.pipiolo.itemshop.domain.delivery;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Delivery extends BaseEntity {

//    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
//    private Order order;

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }

}

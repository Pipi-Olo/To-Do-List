package com.pipiolo.itemshop.domain.comment;

import com.pipiolo.itemshop.domain.BaseEntity;
import com.pipiolo.itemshop.domain.item.Item;
import com.pipiolo.itemshop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class Comment extends BaseEntity {

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

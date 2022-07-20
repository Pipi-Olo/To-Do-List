package com.pipiolo.itemshop.domain.user;

import com.pipiolo.itemshop.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @AllArgsConstructor
@Entity
public class User extends BaseEntity {

    private String username;
    private String password;
}

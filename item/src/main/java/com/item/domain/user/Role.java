package com.item.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_SELLER("판매자"),
    ROLE_BUYER("구매자")
    ;

    private final String role;
}

package com.pipiolo.itemshop.web.user;

import com.pipiolo.itemshop.domain.user.User;
import lombok.Data;

@Data
public class UserResponse {

    private String username;

    public UserResponse(User user) {
        this.username = user.getUsername();
    }
}

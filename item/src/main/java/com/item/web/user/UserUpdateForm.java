package com.item.web.user;

import com.item.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public User toEntity() {
        return new User(username, password);
    }
}

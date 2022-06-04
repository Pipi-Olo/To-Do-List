package com.item.web.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

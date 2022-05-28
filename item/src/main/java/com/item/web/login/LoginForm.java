package com.item.web.login;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class LoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}

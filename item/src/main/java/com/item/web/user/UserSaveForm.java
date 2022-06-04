package com.item.web.user;

import com.item.domain.user.Role;
import com.item.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserSaveForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    public User toEntity() {
        return new User(username, password, role);
    }
}

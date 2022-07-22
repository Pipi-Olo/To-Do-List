package com.pipiolo.itemshop.web.user;

import com.pipiolo.itemshop.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserSaveForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public User toEntity() {
        return new User(username, password);
    }
}

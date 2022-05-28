package com.item.web.user;

import com.item.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserSaveForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserSaveForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toEntity() {
        return new User(username, password);
    }
}

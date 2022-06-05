package com.item.web.user;

import com.item.domain.user.Role;
import com.item.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/userinfo")
    public String user(Model model, Principal principal) {
        UserResponseForm findUser = userService.findByUsername(principal.getName());
        model.addAttribute("user", findUser);
        return "user/user";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserSaveForm());
        return "user/addUserForm";
    }

    @PostMapping("/add")
    public String addUser(@Validated @ModelAttribute("user") UserSaveForm saveForm,
                          BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "user/addUserForm";
        }

        try {
            userService.save(saveForm);
        } catch (Exception ex) {
            bindingResult.reject("globalError", ex.getMessage());
            return "user/addUserForm";
        }

        return "redirect:/login";
    }

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

}

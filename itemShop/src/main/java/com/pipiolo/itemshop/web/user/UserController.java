package com.pipiolo.itemshop.web.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

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
            return "user/addUserForm";
        }

        userService.save(saveForm);
        return "redirect:/login";
    }
}

package com.item.web.user;

import com.item.domain.item.Item;
import com.item.domain.user.Role;
import com.item.web.item.ItemRequestForm;
import com.item.web.item.ItemSaveForm;
import com.item.web.item.ItemService;
import com.item.web.item.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final ItemService itemService;
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

    @GetMapping("/items/add")
    public String addItemForm(Model model) {
        model.addAttribute("item", new ItemSaveForm());
        return "user/addItemForm";
    }

    @PostMapping("/items/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm saveForm,
                          BindingResult bindingResult,
                          Principal principal,
                          RedirectAttributes redirectAttributes
    ) {
        validate(saveForm, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "user/addItemForm";
        }

        Item savedItem = itemService.save(principal.getName(), saveForm);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        return "redirect:/users/userinfo";
    }

    @GetMapping("/items/{itemId}/edit")
    public String editItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "user/editItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String editItem(@PathVariable Long itemId,
                           @Validated @ModelAttribute("item") ItemUpdateForm updateForm,
                           BindingResult bindingResult
    ) {
        validate(updateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "user/editItemForm";
        }

        itemService.update(itemId, updateForm);
        return "redirect:/users/userinfo";
    }

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

    private void validate(ItemRequestForm requestForm, BindingResult bindingResult) {
        if (requestForm.getPrice() != null && requestForm.getQuantity() != null) {
            int totalPrice = requestForm.getPrice() * requestForm.getQuantity();
            if (totalPrice < 10000) {
                bindingResult.reject("globalError", "가격 * 수량의 값은 10000 초과이어야 합니다. 현재 값 = " + totalPrice); // ObjectError
            }
        }
    }

}

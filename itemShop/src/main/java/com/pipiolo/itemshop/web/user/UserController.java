package com.pipiolo.itemshop.web.user;

import com.pipiolo.itemshop.domain.delivery.Address;
import com.pipiolo.itemshop.web.order.OrderSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping
    public void test() {
        userService.save(new UserSaveForm("test", "test"));
    }
}

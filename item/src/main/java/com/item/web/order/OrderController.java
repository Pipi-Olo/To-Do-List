package com.item.web.order;

import com.item.domain.item.Item;
import com.item.web.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/orders")
@Controller
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/add")
    public String addOrderForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        model.addAttribute("order", new OrderSaveForm());
        return "order/addOrderForm";
    }

    @PostMapping("/add")
    public String addOrder(@PathVariable Long itemId,
                           @Validated @ModelAttribute("order") OrderSaveForm saveForm,
                           BindingResult bindingResult,
                           Principal principal,
                           Model model
    ) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);

        validate(findItem, saveForm, bindingResult);
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "order/addOrderForm";
        }

        orderService.save(findItem, principal.getName(), saveForm);

        return "redirect:/items/{itemId}";
    }

    private void validate(Item item, OrderSaveForm saveForm, BindingResult bindingResult) {
        if (item.getItemName() != null && saveForm.getQuantity() != null) {
            if (item.getQuantity() < saveForm.getQuantity()) {
                bindingResult.reject("globalError", "상품의 수량이 부족합니다.");
            }
        }
    }
}

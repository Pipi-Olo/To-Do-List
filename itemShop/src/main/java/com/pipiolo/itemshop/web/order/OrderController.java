package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.web.item.ItemResponse;
import com.pipiolo.itemshop.web.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/orders")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping
    public String orders(Principal principal, Model model) {
        List<OrderResponse> orders = orderService.findAllByUsername("userA");
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/{orderId}")
    public String order(@PathVariable Long orderId, Model model) {
        OrderDetailResponse findOrder = orderService.findById(orderId);
        model.addAttribute("order", findOrder);
        return "order/order";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        List<ItemResponse> items = itemService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("order", new OrderSaveForm());
        return "order/addOrderForm";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute("order") OrderSaveForm orderSaveForm,
                           Principal principal,
                           RedirectAttributes redirectAttributes
    ) {
        Long orderId = orderService.save(orderSaveForm, "userA");
        OrderDetailResponse findOrder = orderService.findById(orderId);

        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/orders/{orderId}";
    }
}

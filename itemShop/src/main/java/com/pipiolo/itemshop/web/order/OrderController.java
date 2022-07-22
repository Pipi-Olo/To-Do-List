package com.pipiolo.itemshop.web.order;

import com.pipiolo.itemshop.web.item.ItemResponse;
import com.pipiolo.itemshop.web.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

import static com.pipiolo.itemshop.web.order.OrderDetailResponse.*;

@RequiredArgsConstructor
@RequestMapping("/orders")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping
    public String orders(Principal principal, Model model) {
        List<OrderResponse> orders = orderService.findAllByUsername(principal.getName());
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/{orderId}")
    public String order(@PathVariable Long orderId, Model model) {
        OrderDetailResponse findOrder = orderService.findById(orderId);

        int totalPrice = 0;
        List<OrderItemResponse> orderItems = findOrder.getOrderItems();
        for (OrderItemResponse orderItem : orderItems) {
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();
        }

        model.addAttribute("order", findOrder);
        model.addAttribute("totalPrice", totalPrice);
        return "order/order";
    }

    @GetMapping("/add")
    public String addOrderForm(Model model) {
        List<ItemResponse> items = itemService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("order", new OrderSaveForm());
        return "order/addOrderForm";
    }

    @PostMapping("/add")
    public String addOrder(@Validated @ModelAttribute("order") OrderSaveForm orderSaveForm,
                           BindingResult bindingResult,
                           Principal principal,
                           RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "order/addOrderForm";
        }

        Long orderId = orderService.save(orderSaveForm, principal.getName());

        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/orders/{orderId}";
    }
}

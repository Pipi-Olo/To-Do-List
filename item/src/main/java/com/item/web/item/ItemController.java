package com.item.web.item;

import com.item.domain.item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items")
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/item";
    }

    @GetMapping("/add")
    public String addItemForm(Model model) {
        model.addAttribute("item", new ItemSaveForm());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm saveForm,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes
    ) {
        validate(saveForm, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "item/addForm";
        }

        Item savedItem = itemService.save(saveForm);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId,
                           @Validated @ModelAttribute("item") ItemUpdateForm updateForm,
                           BindingResult bindingResult
    ) {
        validate(updateForm, bindingResult); // ObjectError
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "item/editForm";
        }

        itemService.update(itemId, updateForm);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return "redirect:/items";
    }

    public void validate(ItemRequestForm requestForm, BindingResult bindingResult) {
        if (requestForm.getPrice() != null && requestForm.getQuantity() != null) {
            int totalPrice = requestForm.getPrice() * requestForm.getQuantity();
            if (totalPrice < 10000) {
//                bindingResult.reject("totalPriceMin", new Object[]{10000, totalPrice}, null);
                bindingResult.reject("globalError", "가격 * 수량의 값은 10000 초과이어야 합니다. 현재 값 = " + totalPrice); // ObjectError
            }
        }
    }

    @PostConstruct
    public void init() {
        itemService.save(new ItemSaveForm("ItemA", 10000, 10));
        itemService.save(new ItemSaveForm("ItemB", 20000, 20));
    }
}

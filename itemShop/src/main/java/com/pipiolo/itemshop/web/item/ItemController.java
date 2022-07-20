package com.pipiolo.itemshop.web.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/items")
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String items(@ModelAttribute("itemSearch") ItemSearchCond searchCond, Model model) {
        List<ItemResponse> items = itemService.findAll(searchCond);
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        ItemResponse findItem = itemService.findById(itemId);
        model.addAttribute("item", findItem);
        return "item/item";
    }

}

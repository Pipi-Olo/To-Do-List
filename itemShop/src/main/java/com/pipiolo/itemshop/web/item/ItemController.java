package com.pipiolo.itemshop.web.item;

import com.pipiolo.itemshop.web.comment.CommentResponse;
import com.pipiolo.itemshop.web.comment.CommentService;
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
    private final CommentService commentService;

    @GetMapping
    public String items(@ModelAttribute("itemSearch") ItemSearchCond searchCond, Model model) {
        List<ItemResponse> items = itemService.findAll(searchCond);
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        ItemResponse findItem = itemService.findById(itemId);
        List<CommentResponse> comments = commentService.findAll(itemId);

        model.addAttribute("item", findItem);
        model.addAttribute("comments", comments);
        return "item/item";
    }

}

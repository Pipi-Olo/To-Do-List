package com.item.web.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/comments")
@Controller
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/add")
    public String addCommentForm(@PathVariable Long itemId, Model model) {
        model.addAttribute("itemId", itemId);
        model.addAttribute("comment", new CommentSaveForm());
        return "comment/addCommentForm";
    }

    @PostMapping("/add")
    public String addComment(@PathVariable Long itemId,
                             @ModelAttribute("comment") CommentSaveForm saveForm,
                             BindingResult bindingResult,
                             Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            log.error("errors={}", bindingResult);
            return "item/item";
        }

        commentService.save(itemId, saveForm, principal.getName());

        return "redirect:/items/{itemId}";
    }
}

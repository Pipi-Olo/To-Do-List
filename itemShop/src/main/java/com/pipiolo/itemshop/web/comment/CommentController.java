package com.pipiolo.itemshop.web.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/items/{itemId}/comments")
@Controller
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/add")
    public String addCommentForm(@PathVariable Long itemId, Model model) {
        model.addAttribute("comment", new CommentSaveForm());
        return "comment/addCommentForm";
    }

    @PostMapping("/add")
    public String addComment(
            @PathVariable Long itemId,
            @Validated @ModelAttribute("comment") CommentSaveForm saveForm,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            return "comment/addCommentForm";
        }

        commentService.save(saveForm, principal.getName(), itemId);

        return "redirect:/items/{itemId}";
    }
}

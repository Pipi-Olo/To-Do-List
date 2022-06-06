package com.item.web.comment;

import com.item.domain.comment.Comment;
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
@RequestMapping("/items/{itemId}/comments")
@Controller
public class CommentController {

    private final ItemService itemService;
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

    @GetMapping("/{commentId}/edit")
    public String editCommentForm(@PathVariable Long itemId,
                                  @PathVariable Long commentId,
                                  Principal principal,
                                  Model model
    ) {
        Comment findComment = commentService.findById(commentId);

        if (!findComment.getUser().getUsername().equals(principal.getName())) {
            Item findItem = itemService.findById(itemId);
            model.addAttribute("item", findItem);
            model.addAttribute("error", "본인이 작성한 댓글만 수정 가능합니다.");
            return "item/item";
        }

        model.addAttribute("comment", findComment);
        return "comment/editCommentForm";
    }

    @PostMapping("/{commentId}/edit")
    public String editComment(@PathVariable Long commentId,
                              @Validated @ModelAttribute("comment") CommentUpdateForm updateForm,
                              BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "comment/editCommentForm";
        }

        commentService.update(commentId, updateForm);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return "redirect:/items/{itemId}";
    }

}

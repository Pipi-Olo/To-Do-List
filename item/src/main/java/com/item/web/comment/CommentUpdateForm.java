package com.item.web.comment;

import com.item.domain.comment.Comment;
import com.item.domain.item.Item;
import com.item.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentUpdateForm {

    @NotBlank
    private String message;

    public Comment toEntity(User user, Item item) {
        return new Comment(message, user, item);
    }
}

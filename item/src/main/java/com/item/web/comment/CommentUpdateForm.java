package com.item.web.comment;

import com.item.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentUpdateForm {

    @NotBlank
    private String message;

    @NotBlank
    private String username;

    public Comment toEntity() {
        return new Comment(message, username, null);
    }
}

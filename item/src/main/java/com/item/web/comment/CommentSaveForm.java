package com.item.web.comment;

import com.item.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data @NoArgsConstructor @AllArgsConstructor
public class CommentSaveForm {

    @NotBlank
    private String message;

    public Comment toEntity(String username) {
        return new Comment(message, username);
    }
}

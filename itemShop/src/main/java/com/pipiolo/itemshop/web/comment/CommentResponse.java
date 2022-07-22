package com.pipiolo.itemshop.web.comment;

import com.pipiolo.itemshop.domain.comment.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private String message;
    private String username;

    public CommentResponse(Comment comment) {
        this.message = comment.getMessage();
        this.username = comment.getUser().getUsername();
    }
}

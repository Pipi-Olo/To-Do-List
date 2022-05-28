package com.item.domain.comment;

import lombok.Data;

@Data
public class Comment {

    private Long id;
    
    private String message;
    private String username;

    public Comment(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public Comment(Long id, String message, String username) {
        this.id = id;
        this.message = message;
        this.username = username;
    }

    public void update(Comment update) {
        this.message = update.getMessage();
        this.username = update.getUsername();
    }
}

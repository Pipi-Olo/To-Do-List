package com.item.domain.comment;

import com.item.domain.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private String username;

    @ManyToOne
    private Item item;

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

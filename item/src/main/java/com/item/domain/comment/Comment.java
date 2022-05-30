package com.item.domain.comment;

import com.item.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
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

    public Comment(String message, String username, Item item) {
        this.message = message;
        this.username = username;
        this.item = item;
    }

    public void update(Comment update) {
        this.message = update.getMessage();
        this.username = update.getUsername();
    }
}

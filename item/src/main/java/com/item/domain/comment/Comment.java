package com.item.domain.comment;

import com.item.domain.item.Item;
import com.item.domain.user.User;
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

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    public Comment(String message, User user, Item item) {
        this.message = message;
        this.user = user;
        this.item = item;
    }

    public void update(Comment update) {
        this.message = update.getMessage();
    }
}

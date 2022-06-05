package com.item.domain.user;

import com.item.domain.comment.Comment;
import com.item.domain.item.Item;
import com.item.domain.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private final List<Item> items = new ArrayList<>();

    @OneToMany
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany
    private final List<Order> purchases = new ArrayList<>();

    @OneToMany
    private final List<Order> sales = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void update(User updateParam) {
        this.username = updateParam.getUsername();
        this.password = updateParam.getPassword();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addPurchase(Order order) {
        this.purchases.add(order);
    }

    public void addSale(Order order) {
        this.sales.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

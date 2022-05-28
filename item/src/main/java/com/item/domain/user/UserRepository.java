package com.item.domain.user;

import com.item.web.user.UserSaveForm;
import com.item.web.user.UserUpdateForm;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    public User save(UserSaveForm saveForm) {
        User user = saveForm.toEntity();
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    public Optional<User> findByUsername(String username) {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long userId, UserUpdateForm updateParam) {
        User findUser = findById(userId);
        findUser.update(updateParam.toEntity());
    }

    public void clearStore() {
        store.clear();
    }

}

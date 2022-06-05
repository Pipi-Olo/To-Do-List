package com.item.web.user;

import com.item.domain.item.Item;
import com.item.domain.item.ItemRepository;
import com.item.domain.order.Order;
import com.item.domain.order.OrderRepository;
import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public User save(UserSaveForm saveForm) {
        Optional<User> findUser = userRepository.findByUsername(saveForm.getUsername());
        if (findUser.isPresent()) {
            throw new IllegalStateException("동일한 아이디가 존재합니다.");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        saveForm.setPassword(encoder.encode(saveForm.getPassword()));

        return userRepository.save(saveForm.toEntity());
    }

    @Transactional
    public UserResponseForm findByUsername(String username) {
        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found Username=" + username));

        List<Item> items = itemRepository.findBySeller(findUser);
        List<Order> orders = orderRepository.findByUser(findUser);

        return new UserResponseForm(findUser.getUsername(), items, orders);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(u.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Not Found loginID=" + username));

        return findUser;
    }
}

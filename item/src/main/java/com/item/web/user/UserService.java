package com.item.web.user;

import com.item.domain.user.User;
import com.item.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User save(UserSaveForm saveForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        saveForm.setPassword(encoder.encode(saveForm.getPassword()));

        return userRepository.save(saveForm);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(u.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("Not Found loginID=" + username));

        return findUser;
    }
}

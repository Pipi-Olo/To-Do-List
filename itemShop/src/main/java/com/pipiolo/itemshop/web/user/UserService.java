package com.pipiolo.itemshop.web.user;

import com.pipiolo.itemshop.domain.user.User;
import com.pipiolo.itemshop.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Transactional
    public void save(UserSaveForm saveForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        saveForm.setPassword(encoder.encode(saveForm.getPassword()));
        userRepository.save(saveForm.toEntity());
    }

    public UserResponse findById(Long UserId) {
        User findUser = userRepository.findById(UserId)
                .orElseThrow(() -> new NoSuchElementException("Can Not Find UserId=" + UserId));

        return new UserResponse(findUser);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}

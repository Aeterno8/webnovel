package com.github.aeterno8.webnovel.service.Impl;


import com.github.aeterno8.webnovel.models.User;
import com.github.aeterno8.webnovel.repository.UserRepository;
import com.github.aeterno8.webnovel.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${default.user.username}")
    private String defaultUsername;

    @Value("${default.user.password}")
    private String defaultPassword;

    @Value("${default.user.email}")
    private String defaultEmail;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @PostConstruct
    public User createDefaultUser() {
        Optional<User> existingUser = userRepository.findByUsername(defaultUsername);
        if (existingUser.isEmpty()) {
            User defaultUser = User.builder()
                    .username(defaultUsername)
                    .email(defaultEmail)
                    .hashedPassword(passwordEncoder.encode(defaultPassword))
                    .build();
            return userRepository.save(defaultUser);
        }
        return existingUser.get();
    }
}
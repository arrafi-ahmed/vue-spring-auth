package com.example.vuespringauth.service;

import com.example.vuespringauth.model.User;
import com.example.vuespringauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(OffsetDateTime.now());

        int result = userRepository.saveUser(user);

        return result > 0 ? user : null;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}

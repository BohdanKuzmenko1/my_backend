package com.example.backend.domain.services;

import com.example.backend.adapters.repositiries.UserRepository;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " does not exist."));
    }
    public User deleteUserByEmail(String email) {
        User user = findUserByEmail(email);
        userRepository.delete(user);
        return user;
    }
}

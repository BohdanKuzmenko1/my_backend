package com.example.backend.domain.usecases;

import com.example.backend.adapters.request_response.RegisterRequest;
import com.example.backend.domain.entities.User;
import com.example.backend.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.backend.domain.services.ProfileImageService.getDefaultProfileImage;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final PasswordEncoder passwordEncoder;
    public User createUser(RegisterRequest request) {
        return User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .profileImage(getDefaultProfileImage())
                .build();
    }
}

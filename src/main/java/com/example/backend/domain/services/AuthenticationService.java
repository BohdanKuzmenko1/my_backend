package com.example.backend.domain.services;

import com.example.backend.adapters.request_response.AuthenticationRequest;
import com.example.backend.adapters.request_response.AuthenticationResponse;
import com.example.backend.adapters.request_response.RegisterRequest;
import com.example.backend.adapters.repositiries.UserRepository;
import com.example.backend.config.security.jwt.JwtService;
import com.example.backend.domain.usecases.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final CreateUserUseCase createUserUseCase;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var jwtToken = jwtService.generateToken(userRepository.save(createUserUseCase.createUser(request)));
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("firstname", user.getFirstname());
        claims.put("lastname", user.getLastname());
        claims.put("role", user.getRole());
        var jwtToken = jwtService.generateToken(claims, user);
        return new AuthenticationResponse(jwtToken);
    }
}

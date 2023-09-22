package com.example.backend.adapters.controllers;

import com.example.backend.adapters.request_response.AuthenticationRequest;
import com.example.backend.adapters.request_response.AuthenticationResponse;
import com.example.backend.adapters.request_response.RegisterRequest;
import com.example.backend.domain.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final String registerDescription = "This request accepts the email, password,and name to create a new user and adds it to the +database, and after successful completion of the request,you will receive a JWT token in response.";
    private final String authenticateDescription = "This request accepts email and password to authenticate the user, after successful completion of the request you will receive a JWT token in response.";

    @Operation(
            description = registerDescription,
            summary = "Register new user."
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        log.info("Register new user {}.", request.toString());
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @Operation(
            description = authenticateDescription,
            summary = "Authenticate user."
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Authenticate user {}.", request.toString());
        AuthenticationResponse authenticationResponse =
                authenticationService.authenticate(request);
        return ResponseEntity.ok(authenticationResponse);
    }
}

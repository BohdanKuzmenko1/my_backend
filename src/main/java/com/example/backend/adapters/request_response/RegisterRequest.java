package com.example.backend.adapters.request_response;

public record RegisterRequest(
        String firstname,
        String lastname,
        String email,
        String password) {
}

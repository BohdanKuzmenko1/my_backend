package com.example.backend.adapters.request_response;



public record AuthenticationRequest(
        String email,
        String password) {
}

package com.example.backend.adapters.request_response;

public record PostRequest(
        String userEmail,
        String title,
        String content
) {
}

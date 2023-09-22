package com.example.backend.adapters.request_response;

public record ProfileImageRequest (
        String userEmail,
        String name,
        String originalFileName,
        Long size,
        String contentType,
        byte[] bytes
) {
}

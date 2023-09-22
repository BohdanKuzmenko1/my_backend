package com.example.backend.domain.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ProfileImageNotFoundException extends ResponseStatusException {
    public ProfileImageNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}

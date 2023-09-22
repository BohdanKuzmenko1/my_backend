package com.example.backend.adapters.controllers;

import com.example.backend.adapters.request_response.ResponseError;
import com.example.backend.domain.exceptions.PostNotFoundException;
import com.example.backend.domain.exceptions.ProfileImageNotFoundException;
import com.example.backend.domain.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(
            {
                    UserNotFoundException.class,
                    PostNotFoundException.class,
                    ProfileImageNotFoundException.class
            }
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleEntityNotFoundExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseError(HttpStatus.NOT_FOUND, e.getMessage());
    }
}

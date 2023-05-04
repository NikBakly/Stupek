package com.example.stupek.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(NotFoundException.class)
    ApiError handlerNotFoundException(NotFoundException e) {
        return new ApiError(TypeError.NOT_FOUND, e.getMessage(), LocalDateTime.now());
    }
}

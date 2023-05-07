package com.example.stupek.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    ApiError handlerNotFoundException(NotFoundException e) {
        log.warn(e.getMessage());
        return new ApiError(
                TypeError.NOT_FOUND,
                List.of(new Violation(null, e.getMessage())),
                LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ApiError handlerConstraintViolationException(ConstraintViolationException e) {
        log.warn(e.getMessage());
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        constraintViolation -> new Violation(
                                constraintViolation.getPropertyPath().toString(),
                                constraintViolation.getMessage()
                        )
                )
                .toList();
        return new ApiError(
                TypeError.BAD_REQUEST,
                violations,
                LocalDateTime.now());
    }
}

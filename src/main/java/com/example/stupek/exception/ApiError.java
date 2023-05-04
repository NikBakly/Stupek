package com.example.stupek.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record ApiError(@Enumerated(EnumType.STRING) TypeError typeError,
                       String message,
                       @JsonFormat(pattern = "HH:mm:ss|dd-MM-yyyy") LocalDateTime lastTime) {
}

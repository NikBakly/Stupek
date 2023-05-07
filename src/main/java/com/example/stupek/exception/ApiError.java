package com.example.stupek.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        @Enumerated(EnumType.STRING) TypeError typeError,
        List<Violation> violations,
        @JsonFormat(pattern = "HH:mm:ss|dd-MM-yyyy") LocalDateTime lastTime) {
}

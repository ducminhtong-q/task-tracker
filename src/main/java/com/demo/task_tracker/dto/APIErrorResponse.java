package com.demo.task_tracker.dto;

import java.time.Instant;

public record APIErrorResponse(
        String message,
        int status,
        Instant timestamp
) {
    public APIErrorResponse(String message, int status) {
        this(message, status, Instant.now());
    }
}

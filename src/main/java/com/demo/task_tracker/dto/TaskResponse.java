package com.demo.task_tracker.dto;

import com.demo.task_tracker.model.TaskStatus;

import java.time.Instant;

public record TaskResponse(
        String id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt
) {
}

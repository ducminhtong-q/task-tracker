package com.demo.task_tracker.dto;

import com.demo.task_tracker.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record TaskRequest(
    @NotBlank(message = "Title cannot be blank")
    String title,
    String description,
    TaskStatus status
) {
}

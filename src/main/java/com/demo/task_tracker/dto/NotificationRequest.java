package com.demo.task_tracker.dto;

public record NotificationRequest(
        String taskId,
        String message
) {
}

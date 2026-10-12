package com.demo.task_tracker.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String id) {
        super("Task id not found" + id);
    }
}

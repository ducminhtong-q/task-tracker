package com.demo.task_tracker.service;

import com.demo.task_tracker.client.NotificationClient;
import com.demo.task_tracker.dto.TaskRequest;
import com.demo.task_tracker.dto.TaskResponse;
import com.demo.task_tracker.exception.TaskNotFoundException;
import com.demo.task_tracker.model.Task;
import com.demo.task_tracker.model.TaskStatus;
import com.demo.task_tracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final NotificationClient notificationClient;

    public TaskService(TaskRepository repository, NotificationClient notificationClient) {
        this.repository = repository;
        this.notificationClient = notificationClient;
    }

    public TaskResponse create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status() != null ? request.status() : TaskStatus.TODO);
        task.setCreateAt(Instant.now());

        Task saved = repository.save(task);
        notificationClient.notify(saved.getId(), "Task created: " + saved.getTitle());
        return toResponse(saved);
    }

    public List<TaskResponse> getAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public TaskResponse getById(String id) {
        Task task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    public TaskResponse update(String id, TaskRequest request) {
        Task task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        if (request.status() != null) {
            task.setStatus(request.status());
        }

        Task updated = repository.save(task);
        return toResponse(task);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreateAt()
        );
    }
}

package com.demo.task_tracker.service;

import com.demo.task_tracker.dto.TaskRequest;
import com.demo.task_tracker.dto.TaskResponse;
import com.demo.task_tracker.exception.TaskNotFoundException;
import com.demo.task_tracker.model.Task;
import com.demo.task_tracker.model.TaskStatus;
import com.demo.task_tracker.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void create_shouldDefaultStatusToTodo_whenStatusIsNull() {
        TaskRequest request = new TaskRequest("Học Docker", "Ôn lại docker-compose", null);

        Task savedTask = new Task();
        savedTask.setId("abc123");
        savedTask.setTitle("Học Docker");
        savedTask.setStatus(TaskStatus.TODO);

        when(repository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponse result = service.create(request);

        assertEquals(TaskStatus.TODO, result.status());
        assertEquals("abc123", result.id());
    }

    @Test
    void getById_shouldReturnTask_whenFound() {
        Task task = new Task();
        task.setId("abc123");
        task.setTitle("Học Docker");
        task.setStatus(TaskStatus.DOING);

        when(repository.findById("abc123")).thenReturn(Optional.of(task));

        TaskResponse result = service.getById("abc123");

        assertEquals("Học Docker", result.title());
    }

    @Test
    void getById_shouldThrowException_whenNotFound() {
        when(repository.findById("khong-ton-tai")).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class,
                () -> service.getById("khong-ton-tai"));
    }

    @Test
    void delete_shouldCallRepositoryDeleteById() {
        service.delete("abc123");

        verify(repository, times(1)).deleteById("abc123");
    }
}
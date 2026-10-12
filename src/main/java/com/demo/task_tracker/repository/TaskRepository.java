package com.demo.task_tracker.repository;

import com.demo.task_tracker.model.Task;
import com.demo.task_tracker.model.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String>{
    List<Task> findByStatus(TaskStatus status);
}
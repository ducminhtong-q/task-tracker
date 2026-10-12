package com.demo.task_tracker;

import com.demo.task_tracker.model.Task;
import com.demo.task_tracker.model.TaskStatus;
import com.demo.task_tracker.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@EnableDiscoveryClient
@SpringBootApplication
public class TaskTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner testMongoConnection(TaskRepository repository) {
//		return args -> {
//			Task task = new Task();
//			task.setTitle("Test the first task");
//			task.setStatus(TaskStatus.TODO);
//			task.setCreateAt(Instant.now());
//
//			Task saved = repository.save(task);
//			System.out.println("Saved task with id: " + saved.getId());
//			System.out.println("Sum of tasks in DB: " + repository.count());
//
//		};
//	}

}

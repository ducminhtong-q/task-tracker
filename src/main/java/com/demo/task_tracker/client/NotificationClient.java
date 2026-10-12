package com.demo.task_tracker.client;

import com.demo.task_tracker.dto.NotificationRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class NotificationClient {
    private static final Logger log = LoggerFactory.getLogger(NotificationClient.class);
    private final RestClient restClient;

    public NotificationClient(@LoadBalanced RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    @CircuitBreaker(name = "notificationService", fallbackMethod = "notifyFallback")
    public void notify(String taskId, String message) {
        restClient.post()
                .uri("http://notification-service/api/v1/notifications")
                .body(new NotificationRequest(taskId, message))
                .retrieve()
                .toBodilessEntity();
    }

    private void notifyFallback(String taskId, String message, Throwable throwable) {
        log.warn("Failed to send notification for taskId={}: {}", taskId, throwable.getMessage());
    }
}

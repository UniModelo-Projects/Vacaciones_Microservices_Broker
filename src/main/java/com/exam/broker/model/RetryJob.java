package com.exam.broker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "retry_jobs")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class RetryJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String topicType; // PAYMENTS, ORDERS, PRODUCTS

    @Column(columnDefinition = "text")
    private String payload;

    private String status = "PENDING"; // PENDING, SUCCESS, FAILED
    
    private String stepAStatus = "PENDING";
    private String stepBStatus = "PENDING";
    private String stepCStatus = "PENDING";
    private String stepDStatus = "PENDING";

    private int retryCount = 0;
    private int maxRetries = 5;

    private LocalDateTime nextRunAt = LocalDateTime.now();
    
    @Column(columnDefinition = "text")
    private String lastError;

    private LocalDateTime createdAt = LocalDateTime.now();
}

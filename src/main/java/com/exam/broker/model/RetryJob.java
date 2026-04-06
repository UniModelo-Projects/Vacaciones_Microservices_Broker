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

    // Explicitly added methods to resolve compilation errors

    public UUID getId() {
        return id;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStepAStatus() {
        return stepAStatus;
    }

    public void setStepAStatus(String stepAStatus) {
        this.stepAStatus = stepAStatus;
    }

    public String getStepBStatus() {
        return stepBStatus;
    }

    public void setStepBStatus(String stepBStatus) {
        this.stepBStatus = stepBStatus;
    }

    public String getStepCStatus() {
        return stepCStatus;
    }

    public void setStepCStatus(String stepCStatus) {
        this.stepCStatus = stepCStatus;
    }

    public String getStepDStatus() {
        return stepDStatus;
    }

    public void setStepDStatus(String stepDStatus) {
        this.stepDStatus = stepDStatus;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public LocalDateTime getNextRunAt() {
        return nextRunAt;
    }

    public void setNextRunAt(LocalDateTime nextRunAt) {
        this.nextRunAt = nextRunAt;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

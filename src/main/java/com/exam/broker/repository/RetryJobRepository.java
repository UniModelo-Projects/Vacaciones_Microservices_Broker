package com.exam.broker.repository;

import com.exam.broker.model.RetryJob;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RetryJobRepository extends JpaRepository<RetryJob, UUID> {
    List<RetryJob> findByStatusAndNextRunAtBefore(String status, LocalDateTime now);
}

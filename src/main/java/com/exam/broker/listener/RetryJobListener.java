package com.exam.broker.listener;

import com.exam.broker.model.*;
import com.exam.broker.repository.RetryJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RetryJobListener {

    @Autowired
    private RetryJobRepository repository;

    @KafkaListener(topics = {"payments_retry_jobs", "order_retry_jobs", "product_retry_jobs"}, groupId = "broker-retry-group")
    public void listen(String payload, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("Received message from Kafka topic: " + topic);
        
        RetryJob job;
        if (topic.contains("payments")) {
            job = new PaymentRetryJob();
        } else if (topic.contains("order")) {
            job = new OrderRetryJob();
        } else {
            job = new ProductRetryJob();
        }

        job.setTopicType(topic.replace("_retry_jobs", "").toUpperCase());
        job.setPayload(payload);
        job.setStatus("PENDING");
        job.setNextRunAt(LocalDateTime.now());
        
        repository.save(job);
        System.out.println("Job persisted for topic: " + topic);
    }
}

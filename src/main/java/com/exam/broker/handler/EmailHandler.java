package com.exam.broker.handler;

import com.exam.broker.model.RetryJob;
import com.exam.broker.repository.RetryJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler extends BaseHandler {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private RetryJobRepository repository;

    @Override
    public void handle(RetryJob job) throws Exception {
        if ("SUCCESS".equals(job.getStepBStatus())) {
            if (next != null) next.handle(job);
            return;
        }

        System.out.println("[Step B] Sending success email for: " + job.getTopicType());
        
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        java.util.Map<String, Object> fullPayload = mapper.readValue(job.getPayload(), new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, Object>>() {});
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("dinocodeadvisor@gmail.com");
        message.setSubject("✅ " + job.getTopicType() + " Created Successfully");
        message.setText("Your " + job.getTopicType().toLowerCase() + " has been processed successfully.");
        
        try {
            mailSender.send(message);
            fullPayload.put("sendEmail", java.util.Map.of("status", "SUCCESS", "message", "Email sent correctly"));
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            fullPayload.put("sendEmail", java.util.Map.of("status", "FAILED", "message", e.getMessage()));
        }

        job.setPayload(mapper.writeValueAsString(fullPayload));
        job.setStepBStatus("SUCCESS");
        repository.save(job);

        if (next != null) next.handle(job);
    }
}

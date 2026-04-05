package com.exam.broker.handler;

import com.exam.broker.model.RetryJob;
import com.exam.broker.repository.RetryJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateHandler extends BaseHandler {

    @Autowired
    private RetryJobRepository repository;

    @Override
    public void handle(RetryJob job) throws Exception {
        if ("SUCCESS".equals(job.getStepCStatus())) {
            if (next != null) next.handle(job);
            return;
        }

        System.out.println("[Step C] Updating control record for: " + job.getId());
        
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        java.util.Map<String, Object> fullPayload = mapper.readValue(job.getPayload(), new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, Object>>() {});
        
        fullPayload.put("updateRetryJobs", java.util.Map.of("status", "SUCCESS", "message", job.getTopicType() + " created correctly"));
        
        job.setPayload(mapper.writeValueAsString(fullPayload));
        job.setStepCStatus("SUCCESS");
        repository.save(job);

        if (next != null) next.handle(job);
    }
}

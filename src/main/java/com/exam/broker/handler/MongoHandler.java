package com.exam.broker.handler;

import com.exam.broker.model.RetryJob;
import com.exam.broker.repository.RetryJobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class MongoHandler extends BaseHandler {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private RetryJobRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(RetryJob job) throws Exception {
        if ("SUCCESS".equals(job.getStepDStatus())) {
            return;
        }

        System.out.println("[Step D] Creating final record in MongoDB for: " + job.getTopicType());
        
        java.util.Map<String, Object> fullPayload = objectMapper.readValue(job.getPayload(), java.util.Map.class);
        java.util.Map<String, Object> document = (java.util.Map<String, Object>) fullPayload.get("data");
        document.put("originalJobId", job.getId().toString());
        document.put("trackingInfo", fullPayload);
        
        mongoTemplate.save(document, job.getTopicType().toLowerCase() + "_final");

        job.setStepDStatus("SUCCESS");
        job.setStatus("SUCCESS"); // Complete the job
        repository.save(job);
        
        if (next != null) next.handle(job);
    }
}

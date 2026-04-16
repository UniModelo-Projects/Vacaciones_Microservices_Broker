package com.exam.broker.client;

import com.exam.broker.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderClient {
    @PostMapping("/ordenes/retry")
    void retry(@RequestBody Order order);
}

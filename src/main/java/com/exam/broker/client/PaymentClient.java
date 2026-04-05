package com.exam.broker.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@FeignClient(name = "payment-service")
public interface PaymentClient {
    @PostMapping("/pagos/retry")
    void retry(@RequestBody Map<String, Object> payload);
}

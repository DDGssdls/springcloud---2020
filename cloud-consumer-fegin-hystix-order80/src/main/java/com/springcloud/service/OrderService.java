package com.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("CLOUD-PROVIDER-HYSTIX-PAYMENT")
public interface OrderService {
    @GetMapping("/payment/hystix/{id}")
    String paymentInfo(@PathVariable("id")Integer id);

    @GetMapping("/payment/hystix/timeout/{id}")
    String paymentInfoTimeOut(@PathVariable("id")Integer id);
}

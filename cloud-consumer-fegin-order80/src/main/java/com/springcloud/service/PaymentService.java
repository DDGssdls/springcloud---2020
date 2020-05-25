package com.springcloud.service;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: DDG
 * @Date: 2020/4/27 09:43
 * @Description:
 */
@FeignClient("CLOUD-PAYMENT-SERVICE")
@Component
public interface PaymentService {
    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);
}

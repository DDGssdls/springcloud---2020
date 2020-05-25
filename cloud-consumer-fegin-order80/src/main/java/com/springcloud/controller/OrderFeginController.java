package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: DDG
 * @Date: 2020/4/27 09:50
 * @Description:
 */
@RestController
@Slf4j
public class OrderFeginController {
    @Resource
    private PaymentService paymentService;
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult paymentById = paymentService.getPaymentById(id);
        return paymentById;
    }
}

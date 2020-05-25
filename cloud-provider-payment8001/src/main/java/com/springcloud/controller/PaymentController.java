package com.springcloud.controller;


import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);

        log.info("插入结果:" + result);
        log.info("返回的结果id:" + payment.getId());
        if (result > 0) {
            return new CommonResult(200, "插入成功使用的端口号是"+ serverPort ,  result);
        } else {
            return new CommonResult(404, "插入失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        log.info("查询结果:" + paymentById);
        if (paymentById != null) {
            return new CommonResult(200, "插入成功使用的端口是"+ serverPort , paymentById);
        } else {
            return new CommonResult(404, "没有对应记录，查询id:" + id, null);
        }
    }
    @GetMapping("/payment/fegin/timeout")
    public CommonResult getTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CommonResult commonResult =  new CommonResult<>();
        commonResult.setData(serverPort);
        return commonResult;
    }


}

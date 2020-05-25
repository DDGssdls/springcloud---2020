package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DDG
 * @Date: 2020/5/3 12:20
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystix/{id}")
    public String paymentInfo(@PathVariable("id")Integer id) {
        String result = paymentService.paymentInfo(id);
        log.info("result ----->{}", result);
        return result;
    }
    @GetMapping("/payment/hystix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })//需要注意的就是在服务端 和 client端都能进行熔断的设置 但是一般是设置在client端的
    public String paymentInfoTimeOut(@PathVariable("id")Integer id) {
        String result = paymentService.paymentInfoTimeOut(id);
        log.info("result ----->{}", result);
        return result;
    }
    public String paymentInfoHandler(Integer id) {
        return  "出错了 ！！！！" + Thread.currentThread().getName();
    }
}

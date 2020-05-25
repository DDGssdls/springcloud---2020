package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: DDG
 * @Date: 2020/5/3 14:41
 * @Description:
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/payment/hystix/{id}")
    public String paymentInfo(@PathVariable("id")Integer id){
        String result = orderService.paymentInfo(id);
        return result;
    }

    @GetMapping("/payment/hystix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfoTimeOut(@PathVariable("id")Integer id){
        String result = orderService.paymentInfoTimeOut(id);
        return result;
    }
    public String paymentInfoHandler(Integer id) {
        return  "出错了 ！！！！" + Thread.currentThread().getName();
    }

}

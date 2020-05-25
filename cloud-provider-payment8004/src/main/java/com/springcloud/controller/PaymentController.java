package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: DDG
 * @Date: 2020/4/24 15:11
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/zk")
    public String paymentzk(){
        log.info("server.port {}", serverPort);
        return "springcloud with zookeeper："+serverPort+"\t"+ UUID.randomUUID().toString();
    }

}

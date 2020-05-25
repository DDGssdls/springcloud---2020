package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: DDG
 * @Date: 2020/5/3 11:58
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystixMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystixMain.class, args);
    }



}

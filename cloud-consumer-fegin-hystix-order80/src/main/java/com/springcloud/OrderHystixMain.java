package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: DDG
 * @Date: 2020/5/3 14:33
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystixMain {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystixMain.class, args);
    }
}

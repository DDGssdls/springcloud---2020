package com.springcloud.controller;

import com.myrule.MyRules;
import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.lb.impl.MyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class OrderController {

    // private static final String URL = "http://127.0.0.1:8001";
    // 地址是不能写死的 而是使用服务名称来的
    private static final String URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private MyLoadBalancer myLoadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(URL + "/payment/create",payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(URL+"/payment/get/"+ id, CommonResult.class);
    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(400, "shibaile ");
        }
    }
    @GetMapping("/consumer/payment/lb")
    public CommonResult getPayment2(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (!CollectionUtils.isEmpty(instances)){
            ServiceInstance instances1 = myLoadBalancer.instances(instances);
            URI uri = instances1.getUri();
           return  restTemplate.getForObject(uri + "/payment/get/31", CommonResult.class);
        }
        return new CommonResult();
    }

}

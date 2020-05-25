package com.springcloud.lb.impl;

import com.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: DDG
 * @Date: 2020/4/26 14:56
 * @Description:
 */
@Component
public class MyLoadBalancer implements LoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE? 0 : current + 1;

        }while(! this.atomicInteger.compareAndSet(current, next));
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        if (!CollectionUtils.isEmpty(serviceInstances)){
            int index = getAndIncrement() % serviceInstances.size();
            return serviceInstances.get(index);
        }else {
            throw new RuntimeException("11111");
        }
    }
}

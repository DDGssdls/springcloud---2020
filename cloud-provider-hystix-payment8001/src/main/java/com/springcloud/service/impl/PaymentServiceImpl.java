package com.springcloud.service.impl;

import com.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: DDG
 * @Date: 2020/5/3 12:00
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfo" + id;
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        int timeNumber = 3;
        //int i = 1/0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeOut" + id + " 耗时："+timeNumber+"秒钟";
    }
}

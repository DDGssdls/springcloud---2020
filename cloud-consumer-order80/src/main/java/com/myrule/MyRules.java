package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: DDG
 * @Date: 2020/4/26 14:20
 * @Description:
 */
@Configuration
public class MyRules {
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}

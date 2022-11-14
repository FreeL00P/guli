package com.atguigu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * StaApplication
 *
 * @author fj
 * @date 2022/11/13 14:04
 */
@SpringBootApplication
@MapperScan("com.atguigu.staservice.mapper")
@ComponentScan("com.atguigu")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling//定时任务 七子表达式
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}

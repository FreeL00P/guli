package com.atguigu.serviceorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * OrderApplication
 *
 * @author fj
 * @date 2022/11/12 12:38
 */
@SpringBootApplication
@MapperScan("com.atguigu.serviceorder.mapper")
@ComponentScan(basePackages = {"com.atguigu"})
@EnableDiscoveryClient//nacos注册
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

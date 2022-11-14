package com.atguigu.serviceedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * EduApplication
 *
 * @author fj
 * @date 2022/10/29 19:38
 */
@SpringBootApplication
//@MapperScan("com.atguigu.serviceedu.mapper")
@ComponentScan(basePackages = {"com.atguigu"})
@EnableDiscoveryClient//nacos注册
@EnableFeignClients
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}

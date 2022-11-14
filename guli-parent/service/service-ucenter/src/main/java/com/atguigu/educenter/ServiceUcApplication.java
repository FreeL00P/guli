package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ServiceUcApplication
 *
 * @author fj
 * @date 2022/11/8 11:27
 */
@ComponentScan({"com.atguigu"})
@SpringBootApplication//取消数据源自动配置
@MapperScan("com.atguigu.educenter.mapper")
@EnableDiscoveryClient
public class ServiceUcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }
}


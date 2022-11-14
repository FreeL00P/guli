package com.atguigu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * CmsApplication
 *
 * @author fj
 * @date 2022/11/6 15:16
 */
@SpringBootApplication
@MapperScan("com.atguigu.educms.mapper")
@ComponentScan(basePackages = {"com.atguigu"})
@EnableDiscoveryClient//nacos注册
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}

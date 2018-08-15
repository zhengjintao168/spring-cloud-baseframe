package com.company.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * User: zjt
 * DateTime: 2018/1/31 20:54
 * <p>
 * SpringBootApplication： 标识为一个springboot应用
 * EnableEurekaClient：启用服务注册与发现
 * EnableFeignClients：feign声明式服务调用
 * ServletComponentScan：设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
 * ComponentScan: mybatis扫描
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan
@ComponentScan(basePackages={"com.company"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
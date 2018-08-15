package com.company.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * User: zjt
 * DateTime: 2018/1/14 07:54
 *
 * 配置服务注册中心
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaApplication.class).web(true).run(args);
    }

}
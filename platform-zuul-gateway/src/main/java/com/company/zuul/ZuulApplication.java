package com.company.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

/**
 * User: zjt
 * DateTime: 2018/2/18 21:34
 *
 * ﻿@EnableZuulProxy: 开启zuul的api网关服务功能
 * @EnableDiscoveryClient: 启用服务发现
 * @SpringBootApplication: 标识为一个springboot应用
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }

    /**
     * 通过@RefreshScope开启zuul的配置内容动态化
     * @return
     */
    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

}
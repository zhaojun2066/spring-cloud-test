package com.jufeng.springcloud.test.eureka.consumer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 10:31
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \  初始化RestTemplate，用来真正发起REST请求
 *    @EnableDiscoveryClient注解用来将当前应用加入到服务治理体系中
 */

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumer {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaConsumer.class).web(true).run(args);
    }
}
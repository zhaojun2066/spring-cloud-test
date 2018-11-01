package com.jufeng.springcloud.test.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 10:12
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClients {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                EurekaClients.class)
                .web(true).run(args);
    }
}
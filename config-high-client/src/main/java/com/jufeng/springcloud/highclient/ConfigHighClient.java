package com.jufeng.springcloud.highclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 17:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ConfigHighClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigHighClient.class).web(true).run(args);
    }
}
package com.jufeng.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 16:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 通过启动多个server 注册到eureka 达到高可用
 * \
 */

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigHighServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigHighServer.class).web(true).run(args);
    }
}
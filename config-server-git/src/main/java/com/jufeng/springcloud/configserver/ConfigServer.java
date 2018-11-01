package com.jufeng.springcloud.configserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 15:21
 * \* To change this template use File | Settings | File Templates.
 * \* Description: @EnableConfigServer注解，开启Spring Cloud Config的服务端功能
 * \
 */

@EnableConfigServer
@SpringBootApplication
public class ConfigServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServer.class).web(true).run(args);
    }
}
package com.jufeng.springcloud.upload.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 15:00
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class UploadServer {



    public static void main(String[] args) {
        new SpringApplicationBuilder(UploadServer.class).web(true).run(args);
    }
}
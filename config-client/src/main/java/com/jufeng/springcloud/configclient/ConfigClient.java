package com.jufeng.springcloud.configclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 15:43
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@SpringBootApplication
public class ConfigClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClient.class).web(true).run(args);
    }
}
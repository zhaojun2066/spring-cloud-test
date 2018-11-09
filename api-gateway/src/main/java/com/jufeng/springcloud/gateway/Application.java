package com.jufeng.springcloud.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/9
 * \* Time: 9:45
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 *  @EnableZuulProxy注解开启Zuul的功能。
 *  http://localhost:1701/eureka-client/dc
 * \
 */

@EnableZuulProxy
@SpringCloudApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }



    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }
}
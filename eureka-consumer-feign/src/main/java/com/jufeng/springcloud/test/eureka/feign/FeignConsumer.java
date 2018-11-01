package com.jufeng.springcloud.test.eureka.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 13:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \ 通过@EnableFeignClients注解开启扫描Spring Cloud Feign客户端的功能
 * 通过@FeignClient定义的接口来统一的生命我们需要依赖的微服务接口。而在具体使用的时候就跟调用本地方法
 * 一点的进行调用即可。由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的
 * IRule进行策略扩展。
 * 另外，Feign还整合的Hystrix来实现服务的容错保护，在Dalston版本中，Feign的Hystrix默认是关闭的
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignConsumer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FeignConsumer.class).web(true).run(args);
    }
}
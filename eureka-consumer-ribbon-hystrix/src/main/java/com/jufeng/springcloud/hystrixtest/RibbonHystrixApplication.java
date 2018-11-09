package com.jufeng.springcloud.hystrixtest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 11:02
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * 我们使用了@HystrixCommand来将某个函数包装成了Hystrix命令，这里除了定义服务降级之外，
 * Hystrix框架就会自动的为这个函数实现调用的隔离。所以，依赖隔离、服务降级在使用时候都是一体化实现的，
 * 这样利用Hystrix来实现服务容错保护在编程模型上就非常方便的，并且考虑更为全面。除了依赖隔离、服务降级之外，
 * 还有一个重要元素：断路器。我们将在下一篇做详细的介绍，这三个重要利器构成了Hystrix实现服务容错保护的强力组合拳
 *  在应用主类中使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
 * \
 */

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonHystrixApplication {

    @Bean
    @LoadBalanced  // 通过ribbon 让 RestTemplate 自动进行负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(RibbonHystrixApplication.class).web(true).run(args);
    }
}
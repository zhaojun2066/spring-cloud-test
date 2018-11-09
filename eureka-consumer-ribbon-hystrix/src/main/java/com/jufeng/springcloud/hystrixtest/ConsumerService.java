package com.jufeng.springcloud.hystrixtest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/8
 * \* Time: 16:15
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class ConsumerService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback") //  // 如果当前调用的consumer()方法出现了错误，则执行fallback
    public String consumer() {
        //直接采用服务名（http://eureka-client）的方式，内部就会进行负载均衡
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    public String fallback() {
        return "fallback";
    }
}
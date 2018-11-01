package com.jufeng.springcloud.test.eureka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 10:35
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient; //负载均衡

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/consumer")
    public String dc(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url  = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/dc";
        System.out.println("url: "+ url);
        System.out.println("serviceId: " + serviceInstance.getServiceId());
        System.out.println("serviceUrl: " + serviceInstance.getUri());
        return restTemplate.getForObject(url, String.class);
    }

}
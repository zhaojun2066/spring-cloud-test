package com.jufeng.springcloud.test.eureka.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 11:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {
        //直接采用服务名（http://eureka-client）的方式，内部就会进行负载均衡
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}
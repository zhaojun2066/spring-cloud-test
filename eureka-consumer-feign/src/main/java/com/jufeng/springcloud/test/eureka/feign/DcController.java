package com.jufeng.springcloud.test.eureka.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 13:53
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 通过定义的feign客户端来调用服务提供方的接口
 * \
 */

@RestController
public class DcController {
    @Autowired
    DcClient dcClient;

    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}
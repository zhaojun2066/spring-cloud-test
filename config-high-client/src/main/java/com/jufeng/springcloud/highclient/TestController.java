package com.jufeng.springcloud.highclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: zhaojun(JUENG)
 * \* Date: 2018/11/1
 * \* Time: 17:24
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

@RestController
public class TestController {
    @Value("${info}")
    private String info;

    @RequestMapping("/info")
    public String info() {
        return this.info;
    }

   /* public void setFrom(String from) {
        this.info = from;
    }

    public String getFrom() {
        return info;
    }*/
}
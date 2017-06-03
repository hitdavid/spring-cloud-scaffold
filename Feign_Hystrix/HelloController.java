package com.hitdavid.service.gateway.controller;

import com.hitdavid.service.gateway.i.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David on 2017/6/1.
 */

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello/{fallback}")
    @HystrixCommand(fallbackMethod="helloFallbackMethod")
    public String hello(
        @PathVariable("fallback") String fallback
    ){
        String res = helloService.hello(fallback);

        return "调用服务结果为"+res;
    }

    public String helloFallbackMethod(String fallback){
        return "client fallback 参数值为:"+fallback;
    }
}
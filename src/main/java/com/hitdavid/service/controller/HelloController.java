package com.hitdavid.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by David on 2017/5/19.
 */
@Controller
@RequestMapping("/")
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("hello")
    @ResponseBody
    public String index() throws Exception {
        return "Hello World";
    }

    @RequestMapping(value={"welcome",""}, method = RequestMethod.GET)
    public String welcome(){
        return "page/index";
    }

    @RequestMapping("hello/{fallback}")
    @HystrixCommand(fallbackMethod="helloFallbackMethod")/*调用方式失败后调用helloFallbackMethod*/
    @ResponseBody
    public String hello(
        @PathVariable("fallback") String fallback
    ){

        log.error("new hello call happened");
        if("1".equals(fallback)){
            throw new RuntimeException("...");
        }
        return "hello";
    }

    public String helloFallbackMethod(String fallback){
        return "fallback 参数值为:"+fallback;
    }

}

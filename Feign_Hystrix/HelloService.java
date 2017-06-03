package com.hitdavid.service.gateway.i;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cloud-server")
public interface HelloService {

    @RequestMapping("/hello/{fallback}")
    public String hello(@PathVariable("fallback") String fallback);

}
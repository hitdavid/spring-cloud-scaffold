package com.hitdavid.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by David on 2017/5/31.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("")
    public String index() throws Exception {
        return "page/admin";
    }

    @RequestMapping("/service-instances/{applicationName}")
    @ResponseBody
    public List<ServiceInstance> serviceInstancesByApplicationName(
        @PathVariable String applicationName
    ) {
        return this.discoveryClient.getInstances(applicationName);
    }


}

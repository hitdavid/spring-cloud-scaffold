package com.hitdavid.service.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David on 2017/6/14.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "GZQ API-GATEWAY";
    }
}

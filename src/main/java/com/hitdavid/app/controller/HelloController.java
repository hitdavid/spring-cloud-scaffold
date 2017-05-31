package com.hitdavid.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by David on 2017/5/19.
 */
@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String index() throws Exception {
        return "Hello World";
    }

    @RequestMapping(value={"welcome",""}, method = RequestMethod.GET)
    public String welcome(){
        return "page/index";
    }

}

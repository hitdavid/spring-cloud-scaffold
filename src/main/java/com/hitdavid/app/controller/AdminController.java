package com.hitdavid.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by David on 2017/5/31.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("")
    public String index() throws Exception {
        return "page/admin";
    }

}

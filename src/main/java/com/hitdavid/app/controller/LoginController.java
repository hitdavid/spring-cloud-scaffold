package com.hitdavid.app.controller;

import com.hitdavid.app.dao.model.User;
import com.hitdavid.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by David on 2017/5/21.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="", method= RequestMethod.GET)
    public String index() throws Exception {
        return "page/login";
    }

    @RequestMapping(value="", method= RequestMethod.POST)
    public String login(
        @RequestParam(name = "phone") String phone,
        @RequestParam(name = "password") String password
    ) throws Exception {
        User u = userService.login(phone, password);
        return u.toString();
    }
}

package com.hitdavid.app.controller;

import com.hitdavid.app.dao.model.User;
import com.hitdavid.app.exception.JsonException;
import com.hitdavid.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by David on 2017/5/22.
 */

@RestController
@RequestMapping(value="/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired private UserService userService;

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String createUser(
        @RequestParam(name = "phone") String phone,
        @RequestParam(name = "password") String password
    ) throws JsonException {
        userService.createUser(phone, password);
        return "success";
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public String getUser(
        @PathVariable(name = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session
    ) throws JsonException {
        User u = userService.findById(id);
        return u.toString();
    }
}

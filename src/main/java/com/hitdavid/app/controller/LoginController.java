package com.hitdavid.app.controller;

import com.hitdavid.app.dao.model.Role;
import com.hitdavid.app.dao.model.User;
import com.hitdavid.app.service.UserService;
import com.hitdavid.app.session.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by David on 2017/5/21.
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SessionHelper sessionHelper;

    @RequestMapping(value="", method= RequestMethod.GET)
    public String index(
        HttpServletRequest request,
        HttpServletResponse response,
        HttpSession session
    ) throws Exception {

        if (session != null) {
            log.info(session.getId());

            ArrayList<Role> roles = sessionHelper.getRoleFormSession();
            if(roles == null) {
                return "page/login";
            }
            else {
                for (Role r : roles) {
                    if (r.getName().equalsIgnoreCase("ROLE_ADMIN")) {
                        return "page/admin";
                    }
                }
                for (Role r : roles) {
                    if (r.getName().equalsIgnoreCase("ROLE_USER")) {
                        return "page/index";
                    }
                }
            }
        }
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

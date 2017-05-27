package com.hitdavid.app.session;

import com.hitdavid.app.dao.model.Role;
import com.hitdavid.app.dao.model.User;
import com.hitdavid.app.security.SecurityUser;
import com.hitdavid.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by David on 2017/5/25.
 */
@ComponentScan
@Service
public class SessionHelper {

    private static final Logger log = LoggerFactory.getLogger(SessionHelper.class);

    @Autowired
    private UserService userService;

//    private static volatile SessionHelper instance = null;
//    private static final Object lock = new Object();
//
//    public static SessionHelper getInstance() {
//
//        if(instance == null) {
//            synchronized (lock) {
//                if(instance == null) {
//                    instance = new SessionHelper();
//                    return instance;
//                }
//            }
//        }
//
//        return instance;
//    }

    public SessionHelper() {
    }

    public ArrayList<Role> getRoleFormSession(HttpSession session) {

        Long uid = getUserIdFromSession(session);
        if(uid == null) {
            return null;
        }
        else {
            ArrayList<Role> roles = new ArrayList<Role>();
            roles = (ArrayList<Role>) userService.getAllRolesById(uid);
            return roles;
        }
    }

    public Long getUserIdFromSession(HttpSession session) {

        if (session != null) {
            log.info(session.getId());
            log.info(session.toString());

            SecurityContext token = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");

            if(token != null) {
                SecurityUser user = (SecurityUser) token.getAuthentication().getPrincipal();
                if(user != null) {
                    Long uid = user.getId();
                    return uid;
                }
            }
        }
        return null;
    }

    public User getUserInfoFromSession(HttpSession session) {

        Long uid = getUserIdFromSession(session);
        if(uid == null) {
            return null;
        }
        else {
            User user = userService.findById(uid);
            return user;
        }
    }
}

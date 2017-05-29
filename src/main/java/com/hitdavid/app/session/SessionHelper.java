package com.hitdavid.app.session;

import com.hitdavid.app.dao.model.Role;
import com.hitdavid.app.security.SecurityUser;
import com.hitdavid.app.security.SecurityUtil;
import com.hitdavid.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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

    public ArrayList<Role> getRoleFormSession() {

        Long uid = getUserIdFromSession();
        if(uid == null) {
            return null;
        }
        else {
            ArrayList<Role> roles = new ArrayList<Role>();
            roles = (ArrayList<Role>) userService.getAllRolesById(uid);
            return roles;
        }
    }

    public Long getUserIdFromSession() {

        Long uid = SecurityUtil.getUid();
        return uid;
    }

    public SecurityUser getUserInfoFromSession(HttpSession session) {

        return SecurityUtil.getUser();
    }
}

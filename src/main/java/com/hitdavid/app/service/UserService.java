package com.hitdavid.app.service;

import com.google.common.base.Preconditions;
import com.hitdavid.app.constant.STATUS;
import com.hitdavid.app.dao.model.Role;
import com.hitdavid.app.dao.model.User;
import com.hitdavid.app.dao.model.UserRole;
import com.hitdavid.app.dao.repository.RoleRepository;
import com.hitdavid.app.dao.repository.UserRepository;
import com.hitdavid.app.dao.repository.UserRoleRepository;
import com.hitdavid.app.security.Md5PasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by David on 2017/5/22.
 */

@Service
@CacheConfig(cacheNames = "users")
public class UserService implements Serializable {

    @Autowired private transient Md5PasswordEncoder md5PasswordEncoder;
    @Autowired private transient UserRepository userRepository;
    @Autowired private transient RoleRepository roleRepository;
    @Autowired private transient UserRoleRepository userRoleRepository;

    public UserService() {

    }

    public void createUser(String phone, String password) {

        String salt = RandomStringUtils.randomAscii(10);
        String pwd = md5PasswordEncoder.encodePassword(password, salt);
        User u = new User(phone, pwd, salt, phone, STATUS.USER_NORMAL.getValue());
        userRepository.save(u);
    }

    public User login(String phone, String password) {

        Preconditions.checkNotNull(password);
        Preconditions.checkNotNull(phone);

        User u = userRepository.findByPhone(phone);
        if(md5PasswordEncoder.isPasswordValid(password, u.getPassword(), u.getSalt())) {
            return u;
        }
        else return null;
    }

    public User findById(Long id) {
        Preconditions.checkNotNull(id);
        return userRepository.findById(id);
    }

    public User findByPhone(String phone) {

        Preconditions.checkNotNull(phone);
        return userRepository.findByPhone(phone);
    }

    public Collection<GrantedAuthority> getAllRoleNamesById(Long userId) {

        List<UserRole> userRoles = userRoleRepository.findByUid(userId);
        if(CollectionUtils.isEmpty(userRoles)){
            //未授权角色
            return new ArrayList<>();
        }
        ArrayList<Long> roleIds = new ArrayList<>();

        for(UserRole ur : userRoles) {
            roleIds.add(ur.getRid());
        }

        List<Role> roles = roleRepository.findAllByIdIn(roleIds);

        Collection<GrantedAuthority> authorities = new HashSet<>();

        for (Role r : roles) {
            if(r.getStatus() == STATUS.ROLE_NORMAL.getValue()) {
                authorities.add(new GrantedAuthority() {
                    @Override public String getAuthority() {
                        return r.getName();
                    }
                });
            }
        }

        return authorities;
    }

}

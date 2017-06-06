package com.hitdavid.service.security;

import com.hitdavid.service.dao.model.User;
import com.hitdavid.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService, Serializable {

    @Autowired
    protected UserService userService;


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        User user = userService.findByPhone(phone);

        if(user == null){
            throw new UsernameNotFoundException("phone number not registered");
        }

        SecurityUser userDetails = new SecurityUser(
            user.getId(),
            phone,
            user.getPassword(),
            !user.isDisabled(),
            true,
            true,
            true,
            grantedAuthorities(user.getId()),
            user.getSalt());

        return userDetails;
    }

    protected Collection<GrantedAuthority> grantedAuthorities(Long userId){
        return userService.getAllRoleNamesById(userId);
    }

}
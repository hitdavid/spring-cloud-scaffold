package com.hitdavid.app.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Jonsy
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private Long id;
    private String salt;
    private String phone;

    public SecurityUser(

        Long id,
        String phone,
        String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities,
        String salt) {

        super(phone, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.salt = salt;
        this.id = id;
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

}

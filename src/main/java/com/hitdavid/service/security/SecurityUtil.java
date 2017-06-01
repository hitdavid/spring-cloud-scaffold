package com.hitdavid.service.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Jonsy
 *
 */
public class SecurityUtil {

    public static Long getUid(){
        return getUser() == null ? null : getUser().getId();
    }

    public static SecurityUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if(authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        if(authentication.getPrincipal() instanceof SecurityUser) {
            return (SecurityUser) authentication.getPrincipal();
        }
        return null;
    }

}

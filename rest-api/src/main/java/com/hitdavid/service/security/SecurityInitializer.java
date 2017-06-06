package com.hitdavid.service.security;

import com.hitdavid.service.session.SessionConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by David on 2017/5/24.
 */
@ComponentScan
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityInitializer() {
        super(SecurityConfig.class, SessionConfig.class);
    }
}

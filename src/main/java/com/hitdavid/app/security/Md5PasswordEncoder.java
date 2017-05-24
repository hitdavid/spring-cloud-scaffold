package com.hitdavid.app.security;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 重写此类的目的是改变默认的合并密码和盐的行为， password + "{" + salt.toString() + "}";
 * @author Jonsy
 */
@Component
public class Md5PasswordEncoder extends org.springframework.security.authentication.encoding.Md5PasswordEncoder
    implements Serializable {

    @Override
    protected String mergePasswordAndSalt(String password, Object salt, boolean strict) {
        if (password == null) {
            password = "";
        }


        if ((salt == null) || "".equals(salt)) {
            return password;
        }
        else {
            return password + salt.toString();
        }
    }
}

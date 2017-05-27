package com.hitdavid.app.dao.model;

import com.hitdavid.app.constant.STATUS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/21.
 */

@Entity
@Table(name = "User")
@CacheConfig(cacheNames = "users")
public class User implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String name;
    String password;

    String phone;
    String salt;
    Integer status;

    Date created;
    Date updated;

    @Override
    public String toString() {
        return String.format(
            "User[id=%d, Name='%s', phone='%s']",
            id, name, phone);
    }

    public User() {
        log.info("--->User init");
    }

    public User(String name, String password, String salt, String phone, Integer status) {

        this.name = name;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.status = status;
        this.created = new Date();
        this.updated = new Date();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getStatus() {
        return status;
    }

    public boolean isDisabled() {
        if (this.status == STATUS.USER_BLOCKED.getValue()) {
            return true;
        }
        return false;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}

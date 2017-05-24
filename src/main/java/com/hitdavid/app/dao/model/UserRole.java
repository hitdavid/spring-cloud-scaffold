package com.hitdavid.app.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by David on 2017/5/23.
 */
@Entity
@Table(name = "User_Role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    Long uid;
    Long rid;

    @Override
    public String toString() {
        return String.format(
            "UserRole Relationship [id=%d, User='%s', Role='%s']",
            id, uid, rid);
    }

    public UserRole() {

    }

    public UserRole(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public Long getId() {
        return id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}

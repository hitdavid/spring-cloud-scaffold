package com.hitdavid.service.dao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/5/22.
 */

@Entity
@Table(name = "Role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String name;
    Integer status;

    Date created;
    Date updated;

    public Role() {

    }

    @Override
    public String toString() {
        return String.format(
            "Role[id=%d, Name='%s', status='%s']",
            id, name, status);
    }

    public Role(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
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

    public Long getId() {
        return id;
    }
}

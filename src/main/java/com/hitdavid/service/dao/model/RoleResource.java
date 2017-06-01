package com.hitdavid.service.dao.model;

import javax.persistence.*;

/**
 * Created by David on 2017/5/30.
 */

@Entity
@Table(name = "Role_Resource")
public class RoleResource {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    Long roleId;
    Long resourceId;

    @Override
    public String toString() {
        return String.format(
            "Role-Resource Relationship [id=%d, Role='%s', Resource='%s']",
            id, roleId, resourceId);
    }

    public RoleResource() {

    }

    public RoleResource(Long roleId, Long resourceId) {
        this.roleId = roleId;
        this.resourceId = resourceId;
    }

    public Long getId() {
        return id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}

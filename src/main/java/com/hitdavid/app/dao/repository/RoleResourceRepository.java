package com.hitdavid.app.dao.repository;

import com.hitdavid.app.dao.model.RoleResource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David on 2017/5/30.
 */
public interface RoleResourceRepository extends CrudRepository<RoleResource, Long> {

    List<RoleResource> findAllByRoleId(Long id);
}

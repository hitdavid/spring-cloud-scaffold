package com.hitdavid.service.dao.repository;

import com.hitdavid.service.dao.model.RoleResource;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by David on 2017/5/30.
 */
public interface RoleResourceRepository extends CrudRepository<RoleResource, Long> {

    List<RoleResource> findAllByRoleId(Long id);

    List<RoleResource> findAllByResourceId(Long id);
}

package com.hitdavid.app.dao.repository;

import com.hitdavid.app.dao.model.Role;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 2017/5/22.
 */
@ComponentScan
@Service
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {

    /***
     Long id;

     String name;
     Integer status;

     Date created;
     Date updated;
     */

    Role findById(Long id);
    Role findByName(String name);

    List<Role> findAll();

    List<Role> findAllByIdIn(List<Long> ids);
}
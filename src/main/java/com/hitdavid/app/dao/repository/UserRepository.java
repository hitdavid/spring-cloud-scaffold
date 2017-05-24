package com.hitdavid.app.dao.repository;

import com.hitdavid.app.dao.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by David on 2017/5/21.
 */
@ComponentScan
@Service
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /***
     String id;
     String name;
     String password;
     String phone;
     String salt;
     Integer status;

     Date created;
     Date updated;
     */

    User findById(Long id);
    User findByPhone(String phone);
}

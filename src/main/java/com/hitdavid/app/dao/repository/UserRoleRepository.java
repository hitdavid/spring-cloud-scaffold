package com.hitdavid.app.dao.repository;

import com.hitdavid.app.dao.model.UserRole;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 2017/5/23.
 */
@ComponentScan
@Service
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long> {

    List<UserRole> findByUid(Long uid);
}

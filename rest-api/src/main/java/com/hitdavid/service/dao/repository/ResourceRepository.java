package com.hitdavid.service.dao.repository;

import com.hitdavid.service.dao.model.Resource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 2017/5/30.
 */
@ComponentScan
@Service
public interface ResourceRepository extends CrudRepository<Resource, Long> {

    List<Resource> findAllByIdIn(List<Long> ids);

    List<Resource> findAll();

}

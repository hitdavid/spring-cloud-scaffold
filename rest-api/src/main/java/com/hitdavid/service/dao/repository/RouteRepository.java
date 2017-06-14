package com.hitdavid.service.dao.repository;

import com.hitdavid.service.dao.model.Route;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 2017/6/12.
 */
@ComponentScan
@Service
public interface RouteRepository extends PagingAndSortingRepository<Route, Long> {
    List<Route> findAll();
}

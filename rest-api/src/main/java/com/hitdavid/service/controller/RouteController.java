package com.hitdavid.service.controller;

import com.hitdavid.service.dao.model.Route;
import com.hitdavid.service.dao.repository.RouteRepository;
import com.hitdavid.service.util.JSONExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by David on 2017/6/12.
 */

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired private transient RouteRepository routeRepository;

    @RequestMapping(value={""}, method = RequestMethod.GET)
    public String routeIndex(){
        return "page/route/index";
    }

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String list(){

        List<Route> routes = routeRepository.findAll();

        return JSONExtension.toJSONString(routes);
    }



}

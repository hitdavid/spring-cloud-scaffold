package com.hitdavid.service.zuul;

import com.hitdavid.service.dto.ZuulRouteItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomRouteLocator extends MySimpleRouteLocator implements RefreshableRouteLocator {


    @Autowired ZuulProperties zuulProperties;
    @Autowired ServerProperties server;

    private List<Route> routes = new ArrayList<Route>();


    public final static Logger logger = LoggerFactory.getLogger(CustomRouteLocator.class);

    private JdbcTemplate jdbcTemplate;

    private ZuulProperties properties;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        logger.info("servletPath:{}",servletPath);
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {

        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();

        clearAllZuulRoutes();

        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());

        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());

        //优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {

            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulRoute> locateRoutesFromDB(){

        Map<String, ZuulRoute> routes = new LinkedHashMap<>();

        List<ZuulRouteItem> results = jdbcTemplate.query("select * from Route where enabled = true ", new BeanPropertyRowMapper<>(ZuulRouteItem.class));
        for (ZuulRouteItem result : results) {
            if(org.apache.commons.lang3.StringUtils.isBlank(result.getPath())){
                continue;
            }
            ZuulRoute zuulRoute = new ZuulRoute();
            try {
                org.springframework.beans.BeanUtils.copyProperties(result, zuulRoute);
                zuulRoute.setId(result.getRoute_id());
            } catch (Exception e) {
                logger.error("=============load zuul route info from db with error==============",e);
            }
            routes.put(zuulRoute.getPath(),zuulRoute);
        }
        return routes;
    }


}

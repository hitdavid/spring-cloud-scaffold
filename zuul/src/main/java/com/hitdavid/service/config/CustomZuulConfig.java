package com.hitdavid.service.config;

import com.hitdavid.service.zuul.CustomRouteLocator;
import com.hitdavid.service.zuul.MySimpleRouteLocator;
import com.netflix.config.ConfigurationManager;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.ZuulConfiguration;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class CustomZuulConfig {

    public static CustomRouteLocator simpleRouteLocator;

    @Autowired ZuulProperties zuulProperties;
    @Autowired ServerProperties server;
    @Autowired JdbcTemplate jdbcTemplate;

    @Bean
    public CustomRouteLocator routeLocator() {

        simpleRouteLocator = new CustomRouteLocator( this.server.getServletPrefix(), this.zuulProperties );

        ConfigurationListener configurationListener =
            new ConfigurationListener() {
                @Override
                public void configurationChanged( ConfigurationEvent ce ) {

                    zuulProperties.getRoutes();
                    zuulProperties.getIgnoredPatterns();

                    simpleRouteLocator.doRefresh();
                }
            };

        ConfigurationManager.getConfigInstance().addConfigurationListener( configurationListener );

        simpleRouteLocator.setJdbcTemplate(jdbcTemplate);

        return simpleRouteLocator;
    }

}

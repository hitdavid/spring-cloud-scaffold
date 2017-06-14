package com.hitdavid.service.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.util.RequestUtils;
import org.springframework.core.Ordered;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by David on 2017/6/13.
 */
public class MySimpleRouteLocator implements RouteLocator, Ordered {

    private static final Logger log = LoggerFactory.getLogger(MySimpleRouteLocator.class);

    private static final int DEFAULT_ORDER = 0;

    private ZuulProperties properties;

    private PathMatcher pathMatcher = new AntPathMatcher();

    private String dispatcherServletPath = "/";
    private String zuulServletPath;

    private AtomicReference<Map<String, ZuulProperties.ZuulRoute>> routes = new AtomicReference<>();
    private int order = DEFAULT_ORDER;


    public MySimpleRouteLocator(String servletPath, ZuulProperties properties) {
        this.properties = properties;
        if (StringUtils.hasText(servletPath)) {
            this.dispatcherServletPath = servletPath;
        }

        this.zuulServletPath = properties.getServletPath();
    }

    protected void clearAllZuulRoutes() {
        if(this.routes.get() != null) {
            this.routes.get().clear();
        }
    }

    @Override
    public List<Route> getRoutes() {
        if (this.routes.get() == null) {
            this.routes.set(locateRoutes());
        }
        List<Route> values = new ArrayList<>();
        for (String url : this.routes.get().keySet()) {
            ZuulProperties.ZuulRoute route = this.routes.get().get(url);
            String path = route.getPath();
            values.add(getRoute(route, path));
        }
        return values;
    }

    @Override
    public Collection<String> getIgnoredPaths() {
        return this.properties.getIgnoredPatterns();
    }

    @Override
    public Route getMatchingRoute(final String path) {

        return getSimpleMatchingRoute(path);

    }

    protected Route getSimpleMatchingRoute(final String path) {
        if (log.isDebugEnabled()) {
            log.debug("Finding route for path: " + path);
        }

        if (this.routes.get() == null) {
            this.routes.set(locateRoutes());
        }

        if (log.isDebugEnabled()) {
            log.debug("servletPath=" + this.dispatcherServletPath);
            log.debug("zuulServletPath=" + this.zuulServletPath);
            log.debug("RequestUtils.isDispatcherServletRequest()="
                + RequestUtils.isDispatcherServletRequest());
            log.debug("RequestUtils.isZuulServletRequest()="
                + RequestUtils.isZuulServletRequest());
        }

        String adjustedPath = adjustPath(path);

        ZuulProperties.ZuulRoute route = getZuulRoute(adjustedPath);

        return getRoute(route, adjustedPath);
    }

    protected ZuulProperties.ZuulRoute getZuulRoute(String adjustedPath) {
        if (!matchesIgnoredPatterns(adjustedPath)) {
            for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : this.routes.get().entrySet()) {
                String pattern = entry.getKey();
                log.debug("Matching pattern:" + pattern);
                if (this.pathMatcher.match(pattern, adjustedPath)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    protected Route getRoute(ZuulProperties.ZuulRoute route, String path) {
        if (route == null) {
            return null;
        }
        if (log.isDebugEnabled()) {
            log.debug("route matched=" + route);
        }
        String targetPath = path;
        String prefix = this.properties.getPrefix();
        if (path.startsWith(prefix) && this.properties.isStripPrefix()) {
            targetPath = path.substring(prefix.length());
        }
        if (route.isStripPrefix()) {
            int index = route.getPath().indexOf("*") - 1;
            if (index > 0) {
                String routePrefix = route.getPath().substring(0, index);
                targetPath = targetPath.replaceFirst(routePrefix, "");
                prefix = prefix + routePrefix;
            }
        }
        Boolean retryable = this.properties.getRetryable();
        if (route.getRetryable() != null) {
            retryable = route.getRetryable();
        }
        return new Route(route.getId(), targetPath, route.getLocation(), prefix,
            retryable,
            route.isCustomSensitiveHeaders() ? route.getSensitiveHeaders() : null);
    }

    /**
     * Calculate all the routes and set up a cache for the values. Subclasses can call
     * this method if they need to implement {@link RefreshableRouteLocator}.
     */
    public void doRefresh() {
        this.routes.set(locateRoutes());
    }

    /**
     * Compute a map of path pattern to route. The default is just a static map from the
     * {@link ZuulProperties}, but subclasses can add dynamic calculations.
     */
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        for (ZuulProperties.ZuulRoute route : this.properties.getRoutes().values()) {
            routesMap.put(route.getPath(), route);
        }
        return routesMap;
    }

    protected boolean matchesIgnoredPatterns(String path) {
        for (String pattern : this.properties.getIgnoredPatterns()) {
            log.debug("Matching ignored pattern:" + pattern);
            if (this.pathMatcher.match(pattern, path)) {
                log.debug("Path " + path + " matches ignored pattern " + pattern);
                return true;
            }
        }
        return false;
    }

    private String adjustPath(final String path) {
        String adjustedPath = path;

        if (RequestUtils.isDispatcherServletRequest()
            && StringUtils.hasText(this.dispatcherServletPath)) {
            if (!this.dispatcherServletPath.equals("/")) {
                adjustedPath = path.substring(this.dispatcherServletPath.length());
                log.debug("Stripped dispatcherServletPath");
            }
        }
        else if (RequestUtils.isZuulServletRequest()) {
            if (StringUtils.hasText(this.zuulServletPath)
                && !this.zuulServletPath.equals("/")) {
                adjustedPath = path.substring(this.zuulServletPath.length());
                log.debug("Stripped zuulServletPath");
            }
        }
        else {
            // do nothing
        }

        log.debug("adjustedPath=" + adjustedPath);
        return adjustedPath;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}

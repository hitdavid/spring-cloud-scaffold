package com.hitdavid.service.dao.model;

import javax.persistence.*;

/**
 * Created by David on 2017/6/12.
 */
@Entity
@Table(name = "Route")
public class Route {

    public Route() {

    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * The ID of the route (the same as its map key by default).
     */
    private String routeId;

    /**
     * The path (pattern) for the route, e.g. /foo/**.
     */
    private String path;

    /**
     * The service ID (if any) to map to this route. You can specify a physical URL or
     * a service, but not both.
     */
    private String serviceId;

    /**
     * A full physical URL to map to the route. An alternative is to use a service ID
     * and service discovery to find the physical address.
     */
    private String url;

    /**
     * Flag to determine whether the prefix for this route (the path, minus pattern
     * patcher) should be stripped before forwarding.
     */
    private boolean stripPrefix = true;

    /**
     * Flag to indicate that this route should be retryable (if supported). Generally
     * retry requires a service ID and ribbon.
     */
    private Boolean retryable;

    private String apiName;

    private Boolean enabled;


}

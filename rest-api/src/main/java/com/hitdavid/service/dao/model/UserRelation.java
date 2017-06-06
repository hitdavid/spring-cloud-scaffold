package com.hitdavid.service.dao.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by David on 2017/6/1.
 */

@Entity
@Table(name = "UserRelation")
@CacheConfig(cacheNames = "user_relation")
public class UserRelation implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    Long superiorUid;
    Long inferiorUid;

    Boolean enabled;

    Date created;
}

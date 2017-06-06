package com.hitdavid.service.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by David on 2017/5/23.
 */

@ComponentScan
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 24 * 60 * 60)
public class SessionConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        try {
            JedisConnectionFactory cf = new JedisConnectionFactory();
            cf.setHostName("localhost");
            cf.setPort(6379);
            cf.afterPropertiesSet();
            return cf;
        }
        catch (RedisConnectionFailureException e) {
            e.printStackTrace();
        }
        return null;
    }
}

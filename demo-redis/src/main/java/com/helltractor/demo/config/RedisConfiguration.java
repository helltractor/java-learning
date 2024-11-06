package com.helltractor.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: helltractor
 * @Date: 2024/4/7 13:40
 */

/**
 * Redis配置类
 */

@Configuration
public class RedisConfiguration{
    
    final static Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
    
    /**
     * 创建RedisConnectionFactory对象，当无法使用默认的配置时，可以自定义配置
     * @return
     */
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    /**
     * 创建RedisTemplate对象
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        logger.info("开始创建RedisTemplate对象： {}", redisConnectionFactory);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置redis key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}

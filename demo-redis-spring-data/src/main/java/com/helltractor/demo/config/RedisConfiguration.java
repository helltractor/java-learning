package com.helltractor.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    
    final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
    
    @Value("${spring.data.redis.host}")
    private String host;
    
    @Value("${spring.data.redis.port}")
    private int port;
    
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        logger.info("Start create redisTemplate： {}", redisConnectionFactory);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // set redis connection factory object
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // set redis key serializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
    
}
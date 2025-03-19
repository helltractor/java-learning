package com.helltractor.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class RedisCommandTest extends AbstractIntegrationTest {
    
    @Autowired
    RedisTemplate redisTemplate;
    
    @BeforeEach
    void setUp() {
        // clear redis history data
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }
    
    /**
     * test string
     * set get setex setnx getset
     */
    @Test
    void testString() {
        assertTrue(redis.isRunning());
        
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "helltractor");
        String name = (String) valueOperations.get("name");
        
        assertEquals("helltractor", name);
        
        valueOperations.set("code", "1234", 3, TimeUnit.MINUTES);
        valueOperations.setIfPresent("lock", "1");
        valueOperations.setIfAbsent("lock", "2");
        
        assertEquals("1234", valueOperations.get("code"));
        assertEquals("2", valueOperations.get("lock"));
        
        valueOperations.getAndDelete("code");
        valueOperations.getAndDelete("lock");
        
        assertNull(valueOperations.get("code"));
        assertNull(valueOperations.get("lock"));
    }
    
    /**
     * test hash
     * put get keys delete values
     */
    @Test
    void testHash() {
        assertTrue(redis.isRunning());
        
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        
        hashOperations.put("user", "name", "helltractor");
        hashOperations.put("user", "age", "18");
        
        assertEquals("helltractor", hashOperations.get("user", "name"));
        assertEquals("18", hashOperations.get("user", "age"));
        assertEquals(Set.of("name", "age"), hashOperations.keys("user"));
        assertEquals(List.of("helltractor", "18"), hashOperations.values("user"));
        
        hashOperations.delete("user", "name");
        
        assertNull(hashOperations.get("user", "name"));
    }
    
    /**
     * test list
     * leftpush leftpushall rightpush leftpop rightpop size
     */
    @Test
    void testList() {
        assertTrue(redis.isRunning());
        
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        
        listOperations.leftPush("list", "4");
        listOperations.leftPushAll("list", "1", "2", "3");
        listOperations.rightPush("list", "5");
        
        assertEquals(List.of("3", "2", "1", "4", "5"), listOperations.range("list", 0, -1));
        
        listOperations.leftPop("list");
        listOperations.rightPop("list");
        
        assertEquals(3, listOperations.size("list"));
        assertEquals(List.of("2", "1", "4"), listOperations.range("list", 0, -1));
    }
    
    /**
     * test set
     * add members size intersect union difference remove
     */
    @Test
    void testSet() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        
        setOperations.add("set", "1");
        setOperations.add("set1", "2");
        setOperations.add("set2", "3", "4");
        Set members = setOperations.members("set");
        Long size = setOperations.size("set");
        Set intersect = setOperations.intersect("set", "set1");
        Set union = setOperations.union("set", "set1");
        Set difference = setOperations.difference("set", "set1");
        
        assertEquals(Set.of("1"), members);
        assertEquals(1, size);
        assertEquals(Set.of(), intersect);
        assertEquals(Set.of("1", "2"), union);
        assertEquals(Set.of("1"), difference);
        
        setOperations.remove("set", "1");
        
        assertEquals(Set.of(), setOperations.members("set"));
    }
    
    /**
     * test zset
     * add range rangebyscore incrementscore remove
     */
    @Test
    void testZSet() {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        
        zSetOperations.add("zset", "1", 10);
        zSetOperations.add("zset", "2", 2);
        zSetOperations.add("zset", "3", 3);
        
        Set range = zSetOperations.range("zset", 0, -1);
        Set rangeByScore = zSetOperations.rangeByScore("zset", 1, 2);
        zSetOperations.incrementScore("zset", "2", 20);
        
        assertEquals(Set.of("2", "3", "1"), range);
        assertEquals(Set.of("2"), rangeByScore);
        assertEquals(22, zSetOperations.score("zset", "2"));
        
        zSetOperations.remove("zset", "1");
        
        assertEquals(Set.of("2", "3"), zSetOperations.range("zset", 0, -1));
    }
    
    /**
     * common command
     * keys type
     */
    @Test
    void testCommon() {
        assertTrue(redis.isRunning());
        assertEquals(0, redisTemplate.keys("*").size());
        assertFalse(redisTemplate.hasKey("name"));
    }
    
}
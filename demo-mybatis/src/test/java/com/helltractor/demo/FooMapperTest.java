package com.helltractor.demo;

import com.helltractor.demo.mapper.FooMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext
class FooMapperTest {
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    FooMapper fooMapper;
    
    @Test
    void testH2Database() {
        LocalDateTime now = fooMapper.now();
        logger.info("NOW={}", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        
        assertNotNull(now);
    }
    
}

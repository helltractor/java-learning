package com.helltractor.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.helltractor.demo.config.AutoFillConfig;
import com.helltractor.demo.entity.Boy;
import com.helltractor.demo.entity.Girl;

/**
 * 测试类
 */
@SpringBootTest
@ContextConfiguration(classes = {AutoFillConfig.class})
public class AutoFillTest {
    
    @Test
    public void autoFillTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoFillConfig.class);
        Boy boy = context.getBean("boy", Boy.class);
        Girl girl = (Girl) context.getBean("girl");
        
        boy.buy();
        girl.buy();
        
        String boyBought = boy.buyPrice(35);
        String girlBought = girl.buyPrice(99.8);
        
        System.out.println("男孩买到了：" + boyBought);
        System.out.println("女孩买到了：" + girlBought);
    }
    
}

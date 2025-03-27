package com.helltractor.demo.messaging;

import com.helltractor.demo.ConfluentKafkaContainerCluster;
import com.helltractor.demo.KafkaApplication;
import com.helltractor.demo.message.TestMessage;
import com.helltractor.demo.util.IpUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = KafkaApplication.class)
public class MessagingTest {
    
    final static Logger logger = LoggerFactory.getLogger(MessagingTest.class);
    
    @Autowired
    MessagingFactory messagingFactory;
    
    ConfluentKafkaContainerCluster cluster;
    
    MessageProducer<TestMessage> processorOne;
    MessageProducer<TestMessage> processorTwo;
    MessageProducer<TestMessage> processorThree;
    
    @BeforeEach
    void init() {
        cluster = new ConfluentKafkaContainerCluster("7.4.0", 1, 1);
        cluster.start();
        processorOne = messagingFactory.createMessageProducer(Messaging.Topic.TOPIC_ONE, TestMessage.class);
        processorTwo = messagingFactory.createMessageProducer(Messaging.Topic.TOPIC_TWO, TestMessage.class);
        processorThree = messagingFactory.createMessageProducer(Messaging.Topic.TOPIC_THREE, TestMessage.class);
    }
    
    @AfterEach
    void destroy() {
        cluster.stop();
    }
    
    static class TestConsumer {
        
        final AtomicInteger messageCount = new AtomicInteger();
        
        final long startTime = System.currentTimeMillis();
        
        void processMessages(List<TestMessage> messages) {
            messageCount.addAndGet(messages.size());
            long currentTime = System.currentTimeMillis();
            logger.info("Received {} messages, Total: {}, Elapsed time: {} ms",
                    messages.size(), messageCount.get(), currentTime - startTime);
        }
        
        int getTotalMessages() {
            return messageCount.get();
        }
    }
    
    @Test
    void test() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            TestMessage testMessage = new TestMessage();
            testMessage.message = "Test-" + i;
            processorOne.sendMessages(testMessage);
            processorTwo.sendMessages(testMessage);
            processorThree.sendMessages(testMessage);
        }
        
        TestConsumer testConsumer = new TestConsumer();
        for (Messaging.Topic topic : Messaging.Topic.values()) {
            String groupId = topic.name() + "-" + IpUtil.getHostId();
            messagingFactory.createBatchMessageListener(topic, groupId, testConsumer::processMessages);
        }
        
        Thread.sleep(10000);
        assertEquals(3000, testConsumer.getTotalMessages());
    }
}

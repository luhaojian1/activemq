package com.mq.mq_consumer.consumer;


import com.mq.mq_consumer.MqConsumerApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqConsumerApplication.class)
class DifferentTypeMessageConsumerTest {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void testConsumer(){
        //参数一： 监听的queue 或者 Topic,
        //参数二：
        jmsMessagingTemplate.receive("jms_body");
    }
}
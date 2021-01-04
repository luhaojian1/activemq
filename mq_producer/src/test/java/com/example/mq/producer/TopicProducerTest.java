package com.example.mq.producer;


import com.example.mq.MqApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqApplication.class)
class TopicProducerTest {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    MessageService messageService;

    @Test
    public void testProducer(){
        //参数一： destination queue or Topic,
        //参数二：
        jmsMessagingTemplate.convertAndSend("queue", "send msg to queue");
        jmsMessagingTemplate.convertAndSend("topic1", "send msg to topic");
    }

    @Test
    public void testMapProducer(){
        //参数一： 监听的queue 或者 Topic,
        //参数二：
//       jmsTemplate.convertAndSend("msg_topic", new MessageCreator(){
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                MapMessage mapMessage = session.createMapMessage();
//                mapMessage.setString("msg1","1");
//                mapMessage.setString("msg2","2");
//                mapMessage.setString("msg3","3");
//                mapMessage.setString("msg4","4");
//                return mapMessage;
//            }
//        });
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("msg1","1");
        mapMessage.put("msg2","2");
        mapMessage.put("msg3","3");
        mapMessage.put("msg4","4");
        jmsMessagingTemplate.convertAndSend("msg_topic", mapMessage);
        jmsMessagingTemplate.convertAndSend("queue", "send msg to queue");
    }

    @Test
    public void testTransactionProducer(){
        messageService.sendMessage();

    }
}
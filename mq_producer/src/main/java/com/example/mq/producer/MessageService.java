package com.example.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    //注解，消息事务，原子性，要么全部发送，要么全部回滚
    @Transactional
    public void sendMessage(){
        for (long i = 0; i < 10; i++){
            if (i == 4){
                int J = 10/0;
            }
            jmsMessagingTemplate.convertAndSend("queue", "send msg to queue");
        }
    }
}

package com.mq.mq_consumer.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MsgListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            TextMessage message1= (TextMessage)message;
            try {
                System.out.println(message1.getText());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

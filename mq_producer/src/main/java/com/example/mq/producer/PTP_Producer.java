package com.example.mq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTP_Producer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.0.100:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        /*
            创建session
            参数一： 是否开启事务操作
            参数二： 消息确认机制
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue1");
        MessageProducer producer = session.createProducer(queue);

        for (long i = 0; i < 10; i++){
            TextMessage textMessage = session.createTextMessage("Hello, am queue1. Send Message" + i);
            producer.send(textMessage);
        }


        session.close();
        connection.close();
    }
}

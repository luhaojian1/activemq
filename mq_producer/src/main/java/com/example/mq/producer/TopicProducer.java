package com.example.mq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
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
        Topic topic = session.createTopic("topic");
        MessageProducer producer = session.createProducer(topic);

        for (long i = 0; i < 10; i++){
            TextMessage textMessage = session.createTextMessage("Hello, I am Topic. Send Message" + i);
            producer.send(textMessage);
        }


        session.close();
        connection.close();
    }
}

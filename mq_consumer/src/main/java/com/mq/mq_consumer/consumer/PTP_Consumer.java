package com.mq.mq_consumer.consumer;

import com.mq.mq_consumer.listener.ConsumerListener;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTP_Consumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.222.29.171:61617");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        /*
            创建session
            参数一： 是否开启事务操作
            参数二： 消息确认机制
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue1");
        MessageConsumer consumer =session.createConsumer(queue);
        consumer.setMessageListener((MessageListener) new ConsumerListener());

//        while (true){
//            Message message = consumer.receive();
//            if (message == null){
//                break;
//            }
//            if (message instanceof TextMessage){
//                TextMessage message1= (TextMessage)message;
//                System.out.println(message1.getText());
//            }
//
//        }
    }
}

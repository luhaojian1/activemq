package com.example.mq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class ActiveMQConfig {

    @Bean
    public PlatformTransactionManager createTransactionManager(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory){
        return new JmsTransactionManager(connectionFactory);
    }

//    @Bean
//    public ActiveMQConnectionFactory createConnectionFactory(){
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setTrustAllPackages(true);
//        return activeMQConnectionFactory;
//    }
}

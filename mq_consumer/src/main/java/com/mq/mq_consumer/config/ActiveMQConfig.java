package com.mq.mq_consumer.config;

import org.apache.activemq.RedeliveryPolicy;
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

    @Bean
    public RedeliveryPolicy resetRedeliveryPolicy(){
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(10);
        return redeliveryPolicy;
    }
}

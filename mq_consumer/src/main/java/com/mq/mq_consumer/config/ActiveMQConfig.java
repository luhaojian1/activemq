package com.mq.mq_consumer.config;

import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;


import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class ActiveMQConfig {

//    @Bean
//    public PlatformTransactionManager createTransactionManager(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory){
//        return new JmsTransactionManager(connectionFactory);
//    }
//
//    @Bean
//    public RedeliveryPolicy resetRedeliveryPolicy(){
//        RedeliveryPolicy redeliveryPolicy=   new RedeliveryPolicy();
//        //是否在每次尝试重新发送失败后,增长这个等待时间
//        redeliveryPolicy.setUseExponentialBackOff(true);
//        //重发次数,默认为6次,这里设置为10次,-1表示不限次数
//        redeliveryPolicy.setMaximumRedeliveries(3);
//        //重发时间间隔,默认为1毫秒,设置为10000毫秒
//        redeliveryPolicy.setInitialRedeliveryDelay(10000);
//        //表示没有拖延只有UseExponentialBackOff(true)为true时生效
//        //第一次失败后重新发送之前等待10000毫秒,第二次失败再等待10000 * 2毫秒
//        //第三次翻倍10000 * 2 * 2，以此类推
//        redeliveryPolicy.setBackOffMultiplier(2);
//        //是否避免消息碰撞
//        redeliveryPolicy.setUseCollisionAvoidance(true);
//        //设置重发最大拖延时间360000毫秒 表示没有拖延只有UseExponentialBackOff(true)为true时生效
//        redeliveryPolicy.setMaximumRedeliveryDelay(360000);
//        return redeliveryPolicy;
//    }

    @Bean(name = "topicListenerFactory")
    public JmsListenerContainerFactory<DefaultMessageListenerContainer>
        topicListenerFactory(SingleConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setSubscriptionDurable(true);// Set this to "true" to register a durable subscription,
        connectionFactory.setClientId("A");
        factory.setClientId("A");
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}

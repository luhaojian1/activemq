package com.example.mq.producer;

import com.example.mq.MqApplication;
import com.mq.common.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MqApplication.class)
class JMSBodyTest {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    MessageService messageService;

    //@Transactional
    @Test
    public void testJMSBody(){
        String text = "Text Message Test";

        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("msg1","1");
        mapMessage.put("msg2","2");
        mapMessage.put("msg3","3");

        User user = new User("Allard Lu", 18);


        try{
            jmsMessagingTemplate.convertAndSend("jms_body", mapMessage);
            jmsMessagingTemplate.convertAndSend("jms_body", text);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testObjectMsg(){
        jmsTemplate.send("jms_body",
                new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        User user = new User("Allard Lu", 18);
                        return session.createObjectMessage(user);
                    }
                });
//        User user = new User("Allard Lu", 18);
//        jmsMessagingTemplate.convertAndSend("jms_body", user);
    }

    /*
    * Test StreamMessage
    * */
    @Test
    public void testStreamMessage(){
        jmsTemplate.send("jms_body", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                StreamMessage streamMessage = session.createStreamMessage();
                streamMessage.writeBoolean(true);
                streamMessage.writeInt(10);
                streamMessage.writeString("hello world");
                return streamMessage;
            }
        });
    }

    @Test
    public void testByteStreamMessage(){
        jmsTemplate.send("jms_body", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                BytesMessage message = session.createBytesMessage();
                try{
                    //open file
                    File file = new File("D:\\workItem\\picture.jpg");
                    //build file input stream
                    FileInputStream stream = new FileInputStream(file);
                    //write file to byte[] cache
                    byte[] buffer = new byte[(int)file.length()];
                    stream.read(buffer);
                    //set ByteMessage
                    message.writeBytes(buffer);
                }catch (Exception e){
                    e.printStackTrace();
                }

                return message;
            }
        });
    }
}
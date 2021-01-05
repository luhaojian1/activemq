package com.mq.mq_consumer.listener;

import com.mq.common.model.User;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


//For activeMQ header, only JMSCorrelationID, JMSReplyTo, JMSType can be changed
@Component
public class ConsumerListener {
    @JmsListener(destination = "persistent", containerFactory = "topicListenerFactory")
    public void topicMessage(Message message) {
        if (message instanceof TextMessage){
            TextMessage message1= (TextMessage)message;
            try {
                String msgId = message.getJMSCorrelationID();
                //System.out.println(msgId);
                //System.out.println(message.getJMSPriority());
                System.out.println(message1.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

//    @JmsListener(destination = "msg_topic")
//    public void topic2(Message message) {
//        if (message instanceof TextMessage){
//            TextMessage message1= (TextMessage)message;
//            try {
//                String msgId = message.getJMSCorrelationID();
//                //System.out.println(msgId);
//                //System.out.println(message.getJMSPriority());
//                System.out.println(message1.getText() + "topic2");
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @JmsListener(destination = "msg_topic")
//    public void listenTopic(Message message) {
//        if (message instanceof MapMessage){
//            MapMessage message1= (MapMessage)message;
//            try {
//                System.out.println(message1.getMapNames());
//                System.out.println(message1.getString("msg1"));
//                System.out.println(message1.getString("msg2"));
//                System.out.println(message1.getString("msg4"));
//                System.out.println(message1.getString("msg4"));
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    @JmsListener(destination = "jms_body")
//    public void listenDifferentTypeMessage(Message message) throws JMSException, IOException {
//        if (message instanceof TextMessage){
//            TextMessage message2= (TextMessage)message;
//            System.out.println(((TextMessage) message).getText());
//        }
//        if (message instanceof MapMessage){
//            MapMessage message1= (MapMessage)message;
//            System.out.println(message1.getMapNames());
//            System.out.println(message1.getString("msg1"));
//            System.out.println(message1.getString("msg2"));
//            System.out.println(message1.getString("msg3"));
//        }
//        if (message instanceof ObjectMessage){
//            User user = (User)((ObjectMessage) message).getObject();
//            System.out.println(user.toString());
//        }
//        if (message instanceof StreamMessage){
//            StreamMessage streamMessage = (StreamMessage)message;
//            System.out.println(streamMessage.readBoolean());
//            System.out.println(streamMessage.readInt());
//            System.out.println(streamMessage.readString());
//        }
//        if (message instanceof BytesMessage){
//            BytesMessage bytesMessage = (BytesMessage)message;
//            byte[] output = new byte[(int)bytesMessage.getBodyLength()];
//            bytesMessage.readBytes(output);
//            File file = new File("D:\\workItem\\picture1.jpg");
//            if (!file.exists() ){
//                file.createNewFile();
//            }
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(output);
//            fileOutputStream.close();
//        }
//    }
}

package com.example.tacocloud.services.messagingServices;

import com.example.tacocloud.models.Order;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.JacksonJsonMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//JMS has a few shortcomings, not the least of which is that as a Java specification its use
//is limited to Java applications

//RabbitMQ and Kafka address these shortcomings and are available for other languages and platforms beyond the JVM

@Component
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jms;
    private Destination orderQueue;
    private JacksonJsonMessageConverter messageConverter;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue, JacksonJsonMessageConverter messageConverter){
        this.messageConverter=messageConverter;
        this.jms=jms;
        this.orderQueue= orderQueue;
    }


//    private Message addOrderSource(Message message) throws JMSException{
//        message.setStringProperty("X_ORDER_SOURCE", "WEB");
//        return message;
//    }



//    @Override
//    public void sendOrder(Order order){
//        jms.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(order);
//            }
//        }
//        );
//    }

    //simpler way with lambda and destination in properties
    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend(orderQueue, order, message -> {
                message.setStringProperty("X_ORDER_SOURCE", "WEB");
                return message;
        });
    }


}

package com.example.tacocloud.services;

import com.example.tacocloud.models.Order;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jms;

    private Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue){
        this.jms=jms;
        this.orderQueue= orderQueue;
    }



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
        jms.convertAndSend(orderQueue, order);
    }


}

package com.example.tacocloud.services.messagingServices;
import com.example.tacocloud.models.Order;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.JacksonJsonMessageConverter;
import org.springframework.stereotype.Component;

//pull method
//You explicitly ask the queue for a message
@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;
    private Destination orderQueue;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms, Destination orderQueue, JacksonJsonMessageConverter messageConverter){
        this.jms= jms;
        this.orderQueue=orderQueue;
    }

    public Order receiveOrder() {
        return (Order) jms.receiveAndConvert(orderQueue);
    }
}

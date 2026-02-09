package com.example.tacocloud.services;

import com.example.tacocloud.models.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbit;

    @Autowired
    public RabbitOrderReceiver(RabbitTemplate rabbit){
        this.rabbit=rabbit;
    }

    public Order receiveOrder(){
        return rabbit.receiveAndConvert("tacocloud.orders",
                new ParameterizedTypeReference<Order>() {});
    }
}

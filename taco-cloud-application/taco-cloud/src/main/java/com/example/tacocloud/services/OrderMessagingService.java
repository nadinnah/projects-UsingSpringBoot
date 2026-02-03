package com.example.tacocloud.services;

import com.example.tacocloud.models.Order;
import jakarta.jms.Destination;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

public interface OrderMessagingService {

    void sendOrder(Order order);
}

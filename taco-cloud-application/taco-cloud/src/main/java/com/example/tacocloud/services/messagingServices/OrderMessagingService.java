package com.example.tacocloud.services.messagingServices;

import com.example.tacocloud.models.Order;

public interface OrderMessagingService {

    void sendOrder(Order order);
}

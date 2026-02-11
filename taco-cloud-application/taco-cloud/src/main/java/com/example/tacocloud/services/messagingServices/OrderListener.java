package com.example.tacocloud.services.messagingServices;

import com.example.tacocloud.models.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//JmsListener to “listen” for messages on the tacocloud.order.queue destination
@Component
public class OrderListener {

    //private KitchenUI ui;

    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order){
        //ui.displayOrder(order);
    }
}
//kitchen user interface would need to buffer the orders as
//they arrive to avoid overburdening the kitchen staff

//message listeners are perfect
//fit when messages can be handled quickly

//otherwise, the pull model offered by Jms
//Template seems more fitting.
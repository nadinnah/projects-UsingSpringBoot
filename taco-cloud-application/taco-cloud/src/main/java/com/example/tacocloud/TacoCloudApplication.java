package com.example.tacocloud;

import com.example.tacocloud.models.Order;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.jms.support.converter.JacksonJsonMessageConverter;
import org.springframework.jms.support.converter.MessagingMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Traverson traverson(){
        return new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        JacksonJsonMessageConverter messageConverter =
                new JacksonJsonMessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
}

//Application Context is like a factory and registry for your objects (aka beans).
//it is Spring’s brain for managing your app components

//A bean is just an object that Spring creates, manages, and knows about.
//like any normal Java object (new MyClass()), but Spring controls its lifecycle, wiring, and availability for your whole application

//Spring uses annotations to register classes or methods as beans in the context
//@Component → class is a bean
//@Bean → method returns a bean
//@Autowired → inject bean from context

//Application Context is like a factory and registry for your objects (aka beans).
//it is Spring’s brain for managing your app components

//A bean is just an object that Spring creates, manages, and knows about.
//like any normal Java object (new MyClass()), but Spring controls its lifecycle, wiring, and availability for your whole application

//Spring uses annotations to register classes or methods as beans in the context
//@Component → class is a bean
//@Bean → method returns a bean
//@Autowired → inject bean from context
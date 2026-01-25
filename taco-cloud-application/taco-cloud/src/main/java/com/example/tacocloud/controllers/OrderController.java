package com.example.tacocloud.controllers;

import com.example.tacocloud.configurationPropertyHolders.OrderProps;
import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.User;
import com.example.tacocloud.repositories.OrderRepository;
import com.example.tacocloud.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;
    private OrderProps orderProps;
    UserRepository userRepo;

    public OrderController(OrderRepository orderRepo, OrderProps orderProps, UserRepository userRepo){
        this.orderProps= orderProps;
        this.orderRepo=orderRepo;
        this.userRepo=userRepo;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principle) {

        if(errors.hasErrors()){
            return "orderForm";
        }

        User user = userRepo.findByUsername(principle.getName());
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        order.setUser(user);

        orderRepo.save(order);
        sessionStatus.setComplete(); //Once the order is saved, you don’t need it hanging around in a session anymore.
        //processOrder() method asks for a SessionStatus parameter and
        //calls its setComplete() method to reset the session.
       return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable= PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}

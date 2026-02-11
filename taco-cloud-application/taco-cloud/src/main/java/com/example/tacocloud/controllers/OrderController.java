package com.example.tacocloud.controllers;

import com.example.tacocloud.configurationPropertyHolders.OrderProps;
import com.example.tacocloud.models.Order;
import com.example.tacocloud.models.User;
import com.example.tacocloud.repositories.OrderRepository;
import com.example.tacocloud.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order){
        return orderRepo.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable Long orderId,
                            @RequestBody Order patch) {
        Order order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteOrder(@PathVariable Long orderId){
        try{
            orderRepo.deleteById(orderId);
        }catch(EmptyResultDataAccessException ignored){}
    }
}

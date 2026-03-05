package com.example.flashcardfullstack.controller;

import com.example.flashcardfullstack.model.Card;
import com.example.flashcardfullstack.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/card")
@RestController
public class CardController {

    @Autowired
    CardRepository cardRepo;

    @PostMapping("/add")
    Card addCard(@RequestBody Card newCard){
        return cardRepo.save(newCard);
    }

    @GetMapping
    List<Card> getAllCards(){
        return cardRepo.findAll();
    }
}

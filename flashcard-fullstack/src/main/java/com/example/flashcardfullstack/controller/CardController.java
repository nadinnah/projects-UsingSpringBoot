package com.example.flashcardfullstack.controller;

import com.example.flashcardfullstack.model.Card;
import com.example.flashcardfullstack.model.Deck;
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
    @CrossOrigin("http://localhost:3000/")
    Card addCard(@RequestBody Card newCard){
        return cardRepo.save(newCard);
    }


    @GetMapping
    @CrossOrigin("http://localhost:3000/")
    List<Card> getAllCards(){
        return cardRepo.findAll();
    }
}

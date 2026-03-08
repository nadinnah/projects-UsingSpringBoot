package com.example.flashcardfullstack.controller;

import com.example.flashcardfullstack.model.CardList;
import com.example.flashcardfullstack.model.Deck;
import com.example.flashcardfullstack.repository.CardListRepository;
import com.example.flashcardfullstack.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cardlist")
@RestController
public class CardListController {
    @Autowired
    CardListRepository cardListRepo;

    @GetMapping
    @CrossOrigin("http://localhost:3000/")
    List<CardList> getAllCardLists(){
        return cardListRepo.findAll();
    }

    @GetMapping("/{cardListId}")
    CardList getCardListById(@PathVariable Long cardListId){
        return cardListRepo.findById(cardListId).orElseThrow(() -> new RuntimeException("CardList not found with id " + cardListId));
    }

    @PostMapping("/add")
    CardList addCardList(@RequestBody CardList newCardList){
        return cardListRepo.save(newCardList);
    }
}

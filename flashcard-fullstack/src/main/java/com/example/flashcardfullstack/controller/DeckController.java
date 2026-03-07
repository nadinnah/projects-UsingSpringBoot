package com.example.flashcardfullstack.controller;

import com.example.flashcardfullstack.model.Card;
import com.example.flashcardfullstack.model.Deck;
import com.example.flashcardfullstack.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DeckController {
    @Autowired
    DeckRepository deckRepo;

    @GetMapping
    @CrossOrigin("http://localhost:3000/")
    List<Deck> getAllDecks(){
        return deckRepo.findAll();
    }

}

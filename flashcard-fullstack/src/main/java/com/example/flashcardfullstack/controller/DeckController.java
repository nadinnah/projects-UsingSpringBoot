package com.example.flashcardfullstack.controller;

import com.example.flashcardfullstack.model.Card;
import com.example.flashcardfullstack.model.Deck;
import com.example.flashcardfullstack.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deck")
@RestController
public class DeckController {
    @Autowired
    DeckRepository deckRepo;

    @GetMapping
    @CrossOrigin("http://localhost:3000/")
    List<Deck> getAllDecks(){
        return deckRepo.findAll();
    }

    @GetMapping("/{deckId}")
    Deck getDeckById(@PathVariable Long deckId){
        return deckRepo.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found with id " + deckId));
    }

    @PostMapping("/add")
    Deck addDeck(@RequestBody Deck newDeck){
        return deckRepo.save(newDeck);
    }

}

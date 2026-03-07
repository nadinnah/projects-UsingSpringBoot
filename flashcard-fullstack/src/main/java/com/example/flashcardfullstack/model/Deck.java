package com.example.flashcardfullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue
    private Long id;

    private String deckName;

    @OneToMany(mappedBy = "deck")
    private List<CardList> cardList;


}

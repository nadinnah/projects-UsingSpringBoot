package com.example.flashcardfullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue
    private Long id;

    private ArrayList<CardList>=

    private String deckName;

}

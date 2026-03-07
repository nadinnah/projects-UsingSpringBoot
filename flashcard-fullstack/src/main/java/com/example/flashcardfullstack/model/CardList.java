package com.example.flashcardfullstack.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.naming.spi.NamingManager;
import java.util.ArrayList;
import java.util.HashSet;

@Data
@Entity
public class CardList {
    @Id
    @GeneratedValue
    private Long id;
//    private HashSet<Card> cards;

    private String cardListCategory;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @OneToMany(mappedBy = "cardList")
    private ArrayList<Card> cards;
}

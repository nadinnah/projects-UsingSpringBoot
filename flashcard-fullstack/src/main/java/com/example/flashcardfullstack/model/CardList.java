package com.example.flashcardfullstack.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.naming.spi.NamingManager;
import java.util.ArrayList;
import java.util.HashSet;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class CardList {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "cardlist_sequence"
    )
    private Long id;
//    private HashSet<Card> cards;

    private String cardListCategory;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

}

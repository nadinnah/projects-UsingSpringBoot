package com.example.flashcardfullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Deck {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "deck_sequence"
    )
    private Long id;

    private String deckName;
}

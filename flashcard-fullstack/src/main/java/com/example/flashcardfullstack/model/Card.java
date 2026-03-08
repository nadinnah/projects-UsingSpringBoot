package com.example.flashcardfullstack.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "card_sequence"
    )
    private Long id;

    private String frontText;
    private String backText;

    @ManyToOne
    @JoinColumn(name = "card_list_id")
    private CardList cardList;
}


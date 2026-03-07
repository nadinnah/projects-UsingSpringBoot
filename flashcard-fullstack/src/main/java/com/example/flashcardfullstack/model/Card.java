package com.example.flashcardfullstack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String frontText;
    private String backText;

    @ManyToOne
    @JoinColumn(name = "card_list_id")
    private CardList cardList;
}


package com.example.flashcardfullstack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String frontText;
    private String backText;
}

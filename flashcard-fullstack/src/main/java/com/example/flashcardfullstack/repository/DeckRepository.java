package com.example.flashcardfullstack.repository;

import com.example.flashcardfullstack.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck,Long> {
}

package com.example.flashcardfullstack.repository;

import com.example.flashcardfullstack.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}

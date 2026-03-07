package com.example.flashcardfullstack.repository;

import com.example.flashcardfullstack.model.CardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<CardList,Long> {
}

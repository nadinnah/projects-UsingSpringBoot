package com.example.flashcardfullstack.repository;

import com.example.flashcardfullstack.model.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardDetails, Long> {
}

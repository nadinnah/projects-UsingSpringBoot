package com.example.tacocloud.repositories;

import com.example.tacocloud.models.Taco;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository {
    Taco save(Taco design);
}

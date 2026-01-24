package com.example.tacocloud.services;

import com.example.tacocloud.DTOs.TacoRequestDto;
import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Taco;
import com.example.tacocloud.repositories.IngredientRepository;
import com.example.tacocloud.repositories.TacoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//This is the translation layer
//DTO → Entity → Database
@Service
public class TacoService {
    private final TacoRepository tacoRepo;
    private final IngredientRepository ingredientRepo;

    public TacoService(TacoRepository tacoRepo, IngredientRepository ingredientRepo) {
        this.tacoRepo = tacoRepo;
        this.ingredientRepo = ingredientRepo;
    }

    public Taco createTaco(TacoRequestDto dto) {

        Taco taco = new Taco();
        taco.setName(dto.getName());

        List<Ingredient> ingredients =
                dto.getIngredientIds().stream()
                        .map(id -> ingredientRepo.findById(id).orElseThrow())
                        .toList();

        taco.setIngredients(ingredients);

        return tacoRepo.save(taco);
    }
}

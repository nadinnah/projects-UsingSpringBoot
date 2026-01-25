package com.example.tacocloud.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class TacoRequestDto {
    @NotNull
    @Size(min=5, message = "name must be at least 5 characters long")
    private String name;

    @Size(min=1, message = "must choose at least 1 ingredient")
    private List<String> ingredientIds;

}
//No @Entity
//No @Id
//Pure data

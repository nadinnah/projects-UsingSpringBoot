package com.example.tacocloud.DTOs;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class TacoRequestDto {
    private String name;
    private List<String> ingredientIds;

}
//No @Entity
//No @Id
//Pure data

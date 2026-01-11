package com.example.tacocloud.controllers;

import com.example.tacocloud.models.Ingredient;
import com.example.tacocloud.models.Order;
import com.example.tacocloud.repositories.IngredientRepository;
import com.example.tacocloud.repositories.TacoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.example.tacocloud.models.Taco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository designRepo){
        this.ingredientRepository= ingredientRepository;
        this.designRepo= designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients= new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);
        //i->ingredients.add (lambda) = ingredients::add (method reference(ref to static method))

//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
//        ); this way was before adding repo

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients,
            Ingredient.Type type) {

        return ingredients.stream()
                .filter(ingredient -> ingredient.getType() == type)
                .toList();}

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){

        if(errors.hasErrors()){
            return "design";
        }
        Taco saved= designRepo.save(design);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }
}

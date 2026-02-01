package com.example.tacocloud.services;

import com.example.tacocloud.models.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class IngredientService {
    //IMPORTANT:
    //RestTemplate is only useful when your app must talk to another app over HTTP.
    //If there is no “other app”, RestTemplate has no purpose.

    //This is how Spring talks HTTP as a client
    //you must KNOW the URL
    //if the API changes its paths → your client breaks
    //you are tightly coupled to the server structure

    private Traverson traverson;
    private RestTemplate rest;
    //Your app needs data from a different service.
    //Example:
    //Your app: Taco Cloud
    //Other app: Inventory Service
    //Other app: Payment Service
    //Other app: External API (Google, Stripe, weather, etc.)

    //You cannot call:
    //inventoryService.getIngredient()
    //it’s in another process
    //maybe another server
    //maybe another language
    //maybe owned by another company
    //The only bridge is HTTP. RestTemplate is that bridge.

    IngredientService(RestTemplate rest){
        this.rest= rest;
    }

    public Ingredient getIngredientById(String ingredientId) {
        //getForObject doesnt return HTTP status
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);

        //good with many parameters:
//        Map<String,String> urlVariables = new HashMap<>();
//        urlVariables.put("id", ingredientId);
//        urlVariables.put("id", ingredientId);
//        return rest.getForObject("http://localhost:8080/ingredients/{id}",
//                Ingredient.class, urlVariables);
    }

    //Real-world analogy
    //Repository call:
    //“Hey database, give me row #5.”

    //Method call:
    //“Hey class, give me an object.”

    //RestTemplate call:
    //“Hey another program on the internet, send me some JSON.”

    //option2:
    // inspect the Date header from the response
//    public Ingredient getIngredById(String ingredientId){
//        ResponseEntity<Ingredient> responseEntity = rest.getForEntity("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
//        log.info("Fetched time: " + responseEntity.getHeaders().getDate());
//
//        return responseEntity.getBody();
//    }

    //put
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient,
                ingredient.getId());
    }

    //delete
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient,
                Ingredient.class);
    }

    //when to use URI:
    //search?q=cheese & tacos
    //& BREAKS URL
    //so, URI fixes it safely

//TRAVERSON & RESTTEMPLATE TOGETHER:

// RestTemplate can write and delete resources, but doesn’t make it easy to navigate an API.
// Traverson makes easy work of navigating a HATEOAS-enabled API
// doesn’t do is offer any methods for writing to or deleting from those APIs
//use together to make use of both advantages

    private Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();
        return rest.postForObject(ingredientsUrl,
                ingredient,
                Ingredient.class);
    }
}
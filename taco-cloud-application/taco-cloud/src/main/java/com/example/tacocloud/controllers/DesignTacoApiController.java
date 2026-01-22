package com.example.tacocloud.controllers;

import com.example.tacocloud.configurationPropertyHolders.OrderProps;
import com.example.tacocloud.models.Taco;
import com.example.tacocloud.repositories.OrderRepository;
import com.example.tacocloud.repositories.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path="/api/design", produces = "application/json")
public class DesignTacoApiController{

    TacoRepository tacoRepo;
    OrderProps orderProps;

    DesignTacoApiController(TacoRepository tacoRepo, OrderProps orderProps){
        this.tacoRepo= tacoRepo;
        this.orderProps=orderProps;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page= PageRequest.of(0,orderProps.getPageSize(), Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> findById(@PathVariable Long id){
        Optional<Taco> optTaco= tacoRepo.findById(id);
        if(optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
    }
}

package com.maximillian.graph.maximillian.controllers;

import com.maximillian.graph.maximillian.model.Animal;
import com.maximillian.graph.maximillian.model.Cat;
import com.maximillian.graph.maximillian.model.Dog;
import com.maximillian.graph.maximillian.model.Duck;
import com.maximillian.graph.maximillian.service.AnimalService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
public class AnimalController {
   private final AnimalService animalService;
   private final List<Animal> animals;

    public AnimalController(AnimalService animalService, List<Animal> animals) {
        this.animalService = animalService;
        this.animals = animals;
    }

    @QueryMapping
    public List<Animal> pets(){
        return List.of(
                Cat.catList().get(0),
                Cat.catList().get(1),
                Dog.dogList().get(0),
                Dog.dogList().get(1),
                Duck.duckList().get(0),
                Duck.duckList().get(1)
        );
    }

    @QueryMapping
    public Animal petById(@Argument String id) {
        Animal animal = animals.stream().filter(x -> x.id().equals(id))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        return animal;
    }
}

package com.maximillian.graph.maximillian.model;

import com.maximillian.graph.maximillian.dto.Classification;
import com.maximillian.graph.maximillian.enums.Sound;
import com.maximillian.graph.maximillian.enums.Species;

import java.util.Arrays;
import java.util.List;

public record Dog(String id, String name, int age, Sound noise, Classification classification) implements Animal {
    public static List<Dog> dogList() {
        return Arrays.asList(
                new Dog("Animal-004", "Sparkey", 3, Sound.BARK, getDogClassification()),
                new Dog("Animal-005", "Scooby", 3, Sound.BARK, getDogClassification()),
                new Dog("Animal-006", "Nico", 3, Sound.BARK, getDogClassification())
        );
    }


    @Override
    public Animal getById(String id) {
        return dogList().stream().filter(x -> x.id.equals(id)).findFirst()
                .orElse(null);
    }

    public static Classification getDogClassification(){
        return Classification.of("CANNIS", Species.Mammal);
    }
}

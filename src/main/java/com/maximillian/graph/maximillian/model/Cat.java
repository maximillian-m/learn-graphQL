package com.maximillian.graph.maximillian.model;

import com.maximillian.graph.maximillian.dto.Classification;
import com.maximillian.graph.maximillian.enums.Sound;
import com.maximillian.graph.maximillian.enums.Species;

import java.util.Arrays;
import java.util.List;

public record Cat(String id, String name, int age, Sound noise, Classification classification) implements Animal {
    public static List<Cat> catList() {
        return Arrays.asList(
                new Cat("Animal-001", "Puffy", 3, Sound.MEOWS, getCatClassification()),
                new Cat("Animal-002", "Tiny", 3, Sound.MEOWS, getCatClassification()),
                new Cat("Animal-003", "Niffy", 3, Sound.MEOWS, getCatClassification())
        );
    }

    @Override
    public Animal getById(String id) {
        return catList().stream()
                .filter(x -> x.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Classification getCatClassification(){
        return Classification.of("SMATO", Species.Mammal);
    }
}


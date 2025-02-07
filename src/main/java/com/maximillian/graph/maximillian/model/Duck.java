package com.maximillian.graph.maximillian.model;

import com.maximillian.graph.maximillian.dto.Classification;
import com.maximillian.graph.maximillian.enums.Sound;
import com.maximillian.graph.maximillian.enums.Species;

import java.util.Arrays;
import java.util.List;

public record Duck(String id, String name, int age, Sound noise, Classification classification) implements Animal {

    public static List<Duck> duckList() {
        return Arrays.asList(
                new Duck("Animal-007", "Kareem", 3, Sound.QUACKS, getDuckClassification()),
                new Duck("Animal-008", "Haran", 3, Sound.QUACKS, getDuckClassification()),
                new Duck("Animal-009", "Tylion", 3, Sound.QUACKS, getDuckClassification())
        );
    }

    @Override
    public Animal getById(String id) {
        return duckList().stream().filter(x -> x.id.equals(id)).findFirst()
                .orElse(null);
    }

    public static Classification getDuckClassification(){
        return Classification.of("AVES", Species.Bird);
    }
}

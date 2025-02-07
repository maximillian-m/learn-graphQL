package com.maximillian.graph.maximillian.dto;

import com.maximillian.graph.maximillian.enums.Species;

public record Classification(String name, Species species) {
    public static Classification of(String name, Species species){
        return new Classification(name, species);
    }
}

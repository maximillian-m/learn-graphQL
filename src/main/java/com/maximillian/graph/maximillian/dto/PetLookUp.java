package com.maximillian.graph.maximillian.dto;

import com.maximillian.graph.maximillian.model.Animal;

import java.util.Optional;

public interface PetLookUp {
    Optional<Animal> getById(String id);
}

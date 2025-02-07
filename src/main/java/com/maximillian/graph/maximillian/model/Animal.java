package com.maximillian.graph.maximillian.model;

import com.maximillian.graph.maximillian.dto.Classification;

public interface Animal {
    String id();
    String name();
    int age();
    Classification classification();

    Animal getById(String id);

}

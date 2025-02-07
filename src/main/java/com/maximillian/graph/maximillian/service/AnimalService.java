package com.maximillian.graph.maximillian.service;

import com.maximillian.graph.maximillian.model.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    public List<Cat> retrieveAnimal(){
        return Cat.catList();
    }


}

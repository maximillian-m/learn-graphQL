package com.maximillian.graph.maximillian.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public record Author(String id, String firstName, String lastName){

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Jones .B", "Roland"),
            new Author("author-2", "Herman", "Paul"),
            new Author("author-3", "Carney", "Chukwuemeka")
    );

    public static Author getById(String id){
        return authors.stream().filter(author -> author.id.equalsIgnoreCase(id)).findFirst().orElse(null);
    }
    public static Map<String, Author> getByIds(Set<String> ids){

        List<Author> authorList = authors.stream()
                .filter(author -> ids.contains(author.id))
                .toList();
        return authorList.stream().collect(Collectors.toMap(Author::id, Function.identity()));
    }
}

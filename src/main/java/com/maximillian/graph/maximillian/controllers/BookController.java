package com.maximillian.graph.maximillian.controllers;

import com.maximillian.graph.maximillian.model.Author;
import com.maximillian.graph.maximillian.model.Book;
import com.maximillian.graph.maximillian.dto.SearchInput;
import graphql.GraphQLContext;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class BookController {

    public BookController(BatchLoaderRegistry batchLoaderRegistry){
        batchLoaderRegistry.forTypePair(String.class, Author.class)
                .registerMappedBatchLoader((authorIds, author) -> {
                    Map<String, Author> authorMap = Author.getByIds(authorIds);
                    return Mono.just(authorMap);
                });
    }
    @QueryMapping
    public Book bookById(@Argument String id){
        return Book.getById(id);
    }
    @QueryMapping
    public List<Book> allBooks(GraphQLContext context){
      return Book.allBooks();
    }
//    @SchemaMapping
    //For the cases like this it is recommended to use the data-loader to mitigate the problem resulting from N + 1 complexity
    //Imaging for each list Item of book, it will have to execute a separate query or call an external service for each item, it will
    //introduce great latency to the application and the wait time will increase significantly/
//    public Author author(Book book){
//        return Author.getById(book.authorId());
//    }
    @SchemaMapping
    public CompletableFuture<Author> author(Book book, DataLoader<String, Author> dataLoader){
        return dataLoader.load(book.authorId());
    }
    @MutationMapping
    public Book updateBook(@Argument String name, @Argument String id){
        return Book.modifyBook(name, id);
    }

    @QueryMapping
    public List<Book> searchBook(@Argument SearchInput searchInput){
        return Book.getBooksBySearch(searchInput);
    }
}

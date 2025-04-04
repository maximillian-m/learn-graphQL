package com.maximillian.graph.maximillian.model;

import com.maximillian.graph.maximillian.dto.SearchInput;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public record Book(String id, String name, double rating, int pageCount, String authorId) {

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Harry Potter and the magical wand", 4.0, 223, "author-1"),
            new Book("book-2", "Moby Dickson", 3.5, 501, "author-2"),
            new Book("book-3", "Interview with the Vampire", 2.0, 321, "author-3")
    );

    public static Book getById(String bookId) {
        return books.stream().filter(x -> x.id.equalsIgnoreCase(bookId)).findFirst().orElse(null);
    }

//    public static List<Book> allBooks() {
//        return books;
//    }

    public static List<Book> allBooks() {
        throw new RuntimeException("Not implemented yet");
    }

    public static Book modifyBook(String bookName, String id) {
        OptionalInt optionalInt = IntStream.range(0, books.size())
                .filter(index -> books.get(index).id.equals(id))
                .findFirst();
        if (optionalInt.isPresent()) {
            int index = optionalInt.getAsInt();
            Book book = books.get(index);
            Book newBook = new Book(book.id, bookName, book.rating, book.pageCount, book.authorId);
            books.set(index, newBook);
            return newBook;
        }
        return null;
    }

    public static List<Book> getBooksBySearch(SearchInput searchInput) {
        if (searchInput.maxPage() > 0 && searchInput.minPage() > 0) {
            return books.stream().filter(x -> x.pageCount() >= searchInput.minPage() && x.pageCount <= searchInput.maxPage())
                    .toList();
        }
        return Collections.emptyList();
    }
}

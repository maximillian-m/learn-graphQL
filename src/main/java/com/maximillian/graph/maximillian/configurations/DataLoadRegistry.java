//package com.maximillian.graph.maximillian.configurations;
//
//import com.maximillian.graph.maximillian.model.Author;
//import com.maximillian.graph.maximillian.model.Book;
//import org.dataloader.DataLoader;
//import org.dataloader.DataLoaderRegistry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Executor;
//import java.util.function.Function;
//
//@Configuration
//public class DataLoadRegistry {
//
//
//
//    @Bean
//    public ThreadPoolTaskExecutor dataLoaderTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(10); // Minimum number of threads
//        executor.setMaxPoolSize(20); // Maximum number of threads
//        executor.setQueueCapacity(50); // Queue size
//        executor.setThreadNamePrefix("DataLoaderExecutor-");
//        executor.initialize();
//        return executor;
//    }
//
//
//    private <K, V> DataLoader<K, V> createDataLoader(
//            Function<Set<K>, Map<K, V>> batchLoaderFunction, Executor executor
//    ) {
//        return DataLoader.newMappedDataLoader(keys ->
//                CompletableFuture.supplyAsync(() -> batchLoaderFunction.apply(keys), executor)
//        );
//    }
//    @Bean
//    public DataLoaderRegistry dataLoaderRegistry(Executor dataLoaderExecutor) {
//        DataLoaderRegistry registry = new DataLoaderRegistry();
//
//        // Register DataLoader for Authors
//        registry.register("author", createDataLoader(Author::getByIds,dataLoaderExecutor));
//
//        // Register DataLoader for Publishers
////        registry.register("publisher", createDataLoader(keys -> Publisher.getByIds(keys), dataLoaderExecutor));
////
////        // Register DataLoader for Categories
////        registry.register("category", createDataLoader(keys -> Category.getByIds(keys), dataLoaderExecutor));
//
//        return registry;
//    }
//
//    @SchemaMapping
//    public CompletableFuture<Author> loadAuthor(Book book, DataLoader<String, Author> dataLoader){
//        return dataLoader.load(book.id());
//    }
//
//}

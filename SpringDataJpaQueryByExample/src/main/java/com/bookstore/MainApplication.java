package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.batchAuthors();

            Author author = new Author();
            author.setName("Name_10");

            ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                    .withMatcher("name", startsWith());
            Example example = Example.of(author, exampleMatcher);
            List<Author> authorList = bookstoreService.findByNameWithExample(example);
            authorList.forEach(s -> {
                System.out.println(s.getName());
            });
        };
    }
}

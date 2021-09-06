package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.AuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MainApplication {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;
    private final BookRepository bookRepository;

    public MainApplication(AuthorService authorService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            // query with jpa query
            authorRepository.updateAge("kiendt003");
            Author author = authorRepository.findByNameEquals("kiendt003");
            System.out.println(author.getName() + " - " + author.getAge());
        };
    }
}

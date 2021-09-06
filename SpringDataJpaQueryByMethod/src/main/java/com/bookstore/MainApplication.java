package com.bookstore;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.AuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            System.out.println(authorRepository.findByNameEquals("kiendt002"));
            System.out.println(authorRepository.findByNameLike("kiendt002"));
            System.out.println(authorRepository.findFirstByNameEquals("kiendt002"));
            System.out.println(bookRepository.findByAuthorName("kiendt002"));
            System.out.println(authorRepository.countByNameEquals("kiendt002"));

        };
    }
}

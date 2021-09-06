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
            List<Object[]> result = authorRepository.findMaxBookByAuthorNameWithNamedQuery("kiendt003");
            System.out.println(result.get(0)[0]);
            System.out.println(result.get(0)[1]);

            // query with @Query
            List<Object[]> result2 = authorRepository.findMaxBookByAuthorNameWithAnnotationQuery("kiendt003");
            System.out.println(result2.get(0)[0]);
            System.out.println(result2.get(0)[1]);

            List<Author> result3 = authorRepository.searchAuthor("kiendt003");
            System.out.println(result3.get(0).getName());
            List<Author> result4 = authorRepository.searchAuthorWithParameter("kiendt00");
            result4.forEach(System.out::println);
        };
    }
}

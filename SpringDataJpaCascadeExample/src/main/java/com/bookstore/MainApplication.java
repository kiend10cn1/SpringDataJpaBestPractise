package com.bookstore;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.service.AuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public MainApplication(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            // cascade persists ==> When insert new entity
            // if Author has cascade = CascadeType.PERSIST on Author field, JPA will auto generate a query to insert books after inserting author
//            authorService.tryCascadePersist();

            // cascade merge ==> when update data. child object also update.
//            authorService.tryCascadeMerge();

            // cascade delete ==> when delete entity, child object will delete also.
//            authorService.tryCascadeRemove();

            //cascade refresh ==>when delete entity, child object will delete also.
//            authorService.tryCascadeRefresh();

            //cascade refresh ==>when delete entity, child object will delete also.
//            authorService.tryCascadeDetach();
        };
    }
}

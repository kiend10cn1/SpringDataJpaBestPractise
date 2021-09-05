package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

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
            PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").descending());
            // slice no need query to count, it holds a variable to know next state
            Slice sliceData = bookstoreService.findByNameReturnSlice("Name_", pageRequest);
            sliceData.getContent().forEach(s -> {
                System.out.println(((Author) s).getName());
            });
            if (sliceData.hasNext()) {
                Slice nextSliceData = bookstoreService.findByNameReturnSlice("Name_", sliceData.getPageable().next());
                nextSliceData.getContent().forEach(s -> {
                    System.out.println(((Author) s).getName());
                });
            }

            //   need one query to count total element
            Page pageData = bookstoreService.findByGenreReturnPage("Genre_", pageRequest);
            pageData.getContent().forEach(s -> {
                System.out.println(((Author) s).getGenre());
            });

        };
    }
}

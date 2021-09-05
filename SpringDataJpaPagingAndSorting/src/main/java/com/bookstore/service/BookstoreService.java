package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void batchAuthors() {
        List<Author> authors = new ArrayList<>();
        for (int i = 100; i < 200; i++) {
            Author author = new Author();
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge(18 + i);
            authors.add(author);
            if (i % batchSize == 0 && i > 0) {
                long startTime = System.currentTimeMillis();
                authorRepository.saveAll(authors);
                authors.clear();
                long endTime = System.currentTimeMillis();
                System.out.println("Time execute " + (endTime - startTime));
            }
        }

        if (authors.size() > 0) {
            authorRepository.saveAll(authors);
            authors.clear();
        }
    }

    public Slice<Author> findByNameReturnSlice(String name, Pageable pageable) {
        return authorRepository.findByNameStartingWith(name, pageable);
    }

    public Page<Author> findByGenreReturnPage(String name, Pageable pageable) {
        return authorRepository.findByGenreStartingWith(name, pageable);
    }
}

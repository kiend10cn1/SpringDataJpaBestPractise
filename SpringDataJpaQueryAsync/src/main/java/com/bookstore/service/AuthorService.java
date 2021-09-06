package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findByNameWithExample(Example example) {
        return authorRepository.findAll(example);
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

    public void asyncGetAuthor() {
        authorRepository.findUserByName("Name_100").thenAccept(s -> {
            System.out.println("Receive result in async mode");
            System.out.println(s.getName());
        });
    }

}

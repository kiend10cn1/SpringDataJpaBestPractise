package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository,
                         BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Autowired
    EntityManager entityManager;

}
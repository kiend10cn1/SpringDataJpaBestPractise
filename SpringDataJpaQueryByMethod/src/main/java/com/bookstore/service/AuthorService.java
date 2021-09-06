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

    public void tryCascadePersist() {
        Author author = new Author();
        author.setName("xoanquay");
        author.setGenre("Mail");
        author.setAge(1);
        Book book = new Book();
        book.setTitle("Music 1980 overview");
        book.setPrice(1000000);
        author.setBooks(Arrays.asList(new Book[]{book}));
        authorRepository.save(author);
    }

    public void tryCascadeMerge() {
        // cascade merge ==> when update data. child object will change state to persisten.
        // in this case, an insert query for this book will be execute.
        Author authorMerge = authorRepository.getOne(1l);
        authorMerge.setName("NewName");

        Book book = new Book();
        book.setTitle("TitleCreatedByMerge");
        book.setPrice(1000000);
        authorMerge.getBooks().add(book);
        authorRepository.save(authorMerge);
    }

    public void tryCascadeRemove() {
        // cascade merge ==> when update data. child object will change state to persisten.
        // in this case, an insert query for this book will be execute.
        Author authorMerge = authorRepository.getOne(1l);
        authorMerge.setName("NewName");
        authorRepository.delete(authorMerge);
    }

    public void tryCascadeRefresh() {
        // cascade refresh ==> when parent object update some data, an query select for refresh data of author and book will be executed
        // Hibernate: select author0_.id as id1_0_1_, author0_.age as age2_0_1_, author0_.genre as genre3_0_1_, author0_.name as name4_0_1_, books1_.author_id as author_i5_1_3_, books1_.id as id1_1_3_, books1_.id as id1_1_0_, books1_.author_id as author_i5_1_0_, books1_.isbn as isbn2_1_0_, books1_.price as price3_1_0_, books1_.title as title4_1_0_ from author author0_ left outer join book books1_ on author0_.id=books1_.author_id where author0_.id=?
        Author au = authorRepository.getOne(1l);
        au.setName("Being Refresh");
        entityManager.refresh(au);
    }

    public void tryCascadeDetach() {
        // cascade detach ==> if parent be detach and child object also be detach
        Author au = authorRepository.getOne(1l);
        Book book = au.getBooks().get(0);
        entityManager.detach(au);
        System.out.println(entityManager.contains(au) ? "Contain Author" : "No Contain Author");
        System.out.println(entityManager.contains(book) ? "Contain Book" : "No Contain Book");
    }
    public void tryMerge() {
        // cascade detach ==> if parent be detach and child object also be detach
        Author au = new Author();
        entityManager.merge(au);
    }
}
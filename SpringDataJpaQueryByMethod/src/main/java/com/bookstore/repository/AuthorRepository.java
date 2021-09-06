package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    Author findByNameEquals(String name);

    Author findFirstByNameEquals(String name);


    // find by field name with like
    Author findByNameLike(String name);

    long countByNameEquals(String name);

}
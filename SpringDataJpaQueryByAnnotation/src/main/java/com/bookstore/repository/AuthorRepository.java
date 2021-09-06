package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Object[]> findMaxBookByAuthorNameWithNamedQuery(String name);

    @Query("SELECT e.name, count(e.name) FROM Author e inner join Book b on e.id = b.author.id where e.name = ?1 GROUP BY e.name")
    List<Object[]> findMaxBookByAuthorNameWithAnnotationQuery(String name);

    @Query("SELECT e FROM Author e WHERE e.name LIKE %?1%")
    List<Author> searchAuthor(String name);

    @Query("SELECT e FROM Author e WHERE e.name LIKE %:name%")
    List<Author> searchAuthorWithParameter(@Param("name")String name);
}
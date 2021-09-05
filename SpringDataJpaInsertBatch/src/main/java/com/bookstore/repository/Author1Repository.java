package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.entity.Author1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Author1Repository extends JpaRepository<Author1, Long> {
}

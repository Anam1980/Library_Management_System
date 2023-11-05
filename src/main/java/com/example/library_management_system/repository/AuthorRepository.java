package com.example.library_management_system.repository;

import com.example.library_management_system.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {


    @Query(value = "SELECT a FROM Author a WHERE (SELECT COUNT(b) FROM Book b WHERE b.author.id = a.id) > :book")
    List<Author> findAllByNumberBook(@Param("book") int book);

}

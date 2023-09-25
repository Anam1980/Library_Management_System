package com.example.library_management_system.repository;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JpaRepository<Book, Integer> integer is datatype for pri. key
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book where genre = :genre1 and cost > :cost1", nativeQuery = true)
    List<Book> getBooksByGenreAndCostGreaterThan(String genre1, double cost1);

    @Query(value="select b from book b where b.genre = :genre and b.cost > :cost")
    List<Book> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);
}

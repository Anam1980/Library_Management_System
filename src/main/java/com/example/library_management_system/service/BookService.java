package com.example.library_management_system.service;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.dto.response.ReturnBook;
import com.example.library_management_system.model.Book;

import java.util.List;

public interface BookService {
    String addBook(Book book);

    List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost);

    List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost);

    String deleteBook(int id);

    List<String> getAllgenreBooks(Genre genre);

    List<BookResponse> getBooksByPagesRange(int minPages, int maxPages);

    List<String> getAuthorsByGenre(Genre genre);
}

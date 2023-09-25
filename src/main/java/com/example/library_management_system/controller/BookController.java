package com.example.library_management_system.controller;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){

        try{
            String response = bookService.addBook(book);
            return response;
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    // delete a book

    // give me names of all the books of a particular genre

    // give me names of all the books of a particular genre and cost gretaer than 500 rs
    @GetMapping("/get-books-by-genre-and-cost-greater-than")
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre, @RequestParam("cost") double cost){
        return  bookService.getBooksByGenreAndCostGreaterThan(genre, cost);
    }

    @GetMapping("/get-books-by-genre-and-cost-greater-than-hql")
    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(@RequestParam("genre") Genre genre, @RequestParam("cost") double cost){
        return  bookService.getBooksByGenreAndCostGreaterThanHQL(genre, cost);
    }
    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre

 
}
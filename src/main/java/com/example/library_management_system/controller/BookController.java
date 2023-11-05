package com.example.library_management_system.controller;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @DeleteMapping("/delete_book/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") int id){
         try{
             String response = bookService.deleteBook(id);
             return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
         }
         catch (Exception e){
             return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }

    // give me names of all the books of a particular genre
    @GetMapping("/get_all_genre_books")
    public ResponseEntity getAllgenreBooks(@RequestParam Genre genre){
        try {
            List<String> response = bookService.getAllgenreBooks(genre);
            return  new ResponseEntity<>(response, HttpStatus.FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

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
    @GetMapping("/get-books-by-pages-range")
    public ResponseEntity getBooksByPagesRange(@RequestParam("minPages") int minPages, @RequestParam("maxPages") int maxPages) {
        try {
            List<BookResponse> response = bookService.getBooksByPagesRange(minPages, maxPages);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // give me the names of all the authors who write a particular genre
    @GetMapping("/get-authors-by-genre")
    public ResponseEntity getAuthorsByGenre(@RequestParam Genre genre) {
        try {
            List<String> response = bookService.getAuthorsByGenre(genre);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
package com.example.library_management_system.controller;


import com.example.library_management_system.dto.request.AuthorRequest;
import com.example.library_management_system.dto.response.AuthorResponse;
import com.example.library_management_system.exception.AuthorNotFoundException;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.service.AuthorService;
import com.example.library_management_system.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AuthorRequest authorRequest){
        AuthorResponse response = authorService.addAuthor(authorRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/update-email-author")
    public ResponseEntity updateEmail(@RequestParam("id")int regNo, @RequestParam("newEmail") String newEmail) throws Exception {
        AuthorResponse authorResponse = authorService.updateEmail(regNo, newEmail);
        if(authorResponse==null){
            throw new AuthorNotFoundException("Author with this registration number not found");
        }
        return new ResponseEntity("Email updated successfully",HttpStatus.FOUND);
    }

    // Give me the names of all the books written by a partiular author
    @GetMapping("/get-author-books")
    public  ResponseEntity getAuthorBooks(@RequestBody AuthorRequest authorRequest ){
          try{
              List<String> bookList = authorService.getAuthorBooks(authorRequest);
              return new ResponseEntity<>(bookList, HttpStatus.FOUND);
          }
          catch (Exception e){
              return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
          }
    }


    // give me the names of authors who have written more than 'x' number of books
    @GetMapping("/get-all-author-morethan-books/{noOfBooks}")
    public ResponseEntity getAuthorHavingMorethanBooks(@PathVariable("noOfBooks") int noOfBooks){
        try{
            List<String> authorlist = authorService.getAuthorHavingMorethanBooks(noOfBooks);
            return new ResponseEntity<>(authorlist, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
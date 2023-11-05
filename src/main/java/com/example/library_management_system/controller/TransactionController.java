package com.example.library_management_system.controller;

import com.example.library_management_system.dto.response.IssueBookResponse;
import com.example.library_management_system.dto.response.ReturnBook;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.service.TransactionService;
import com.example.library_management_system.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    BookService bookService;

    @PostMapping("/issueBook/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity issueBook(@PathVariable("book-id") int bookId,
                                    @PathVariable("student-id") int studentId){

        try{
            IssueBookResponse response = transactionService.issueBook(bookId,studentId);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // return book
    @PostMapping("/returnBook/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity returnBook(@PathVariable("book-id") int bookId,
                                    @PathVariable("student-id") int studentId){

        try{
            ReturnBook response = transactionService.returnBook(bookId,studentId);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
package com.example.library_management_system.utils;

import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidationUtils {

    @Autowired
    static
    BookRepository bookRepository;

    public static boolean ValidBook(int id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            return true;
        }
        return false;
    }
}

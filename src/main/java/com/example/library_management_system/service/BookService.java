package com.example.library_management_system.service;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.exception.AuthorNotFoundException;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(Book book) {

        //check if author exists or not
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
       if(authorOptional.isEmpty()){
           throw new AuthorNotFoundException("Invalid author id!!!");

       }

       Author author = authorOptional.get();
       book.setAuthor(author);
       author.getBooks().add(book);

       authorRepository.save(author);//save both author and book
        return "Book added successfully";

    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThan(String genre, double cost) {
        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThan(genre, cost);

        //prepare the response, convert model to dto
        List<BookResponse> responses = new ArrayList<>();
        for(Book book : books){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            responses.add(bookResponse);
        }
        return responses;
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanHQL(Genre genre, double cost) {
        List<Book> books = bookRepository.getBooksByGenreAndCostGreaterThanHQL(genre, cost);

        //prepare the response, convert model to dto
        List<BookResponse> responses = new ArrayList<>();
        for(Book book : books){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            responses.add(bookResponse);
        }
        return responses;
    }
}

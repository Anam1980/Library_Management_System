package com.example.library_management_system.service.impl;

import com.example.library_management_system.Enum.Genre;
import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.dto.response.ReturnBook;
import com.example.library_management_system.exception.AuthorNotFoundException;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.service.BookService;
import com.example.library_management_system.transformer.BookTransformer;
import com.example.library_management_system.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
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

    @Override
    public  String deleteBook(int id) {

        if (!ValidationUtils.ValidBook(id)) {
            return "Invalid BookId";
        }

        bookRepository.deleteById(id);
        return "Book with Id"+id+" has been deleted successfully";
    }

    @Override
    public List<String> getAllgenreBooks(Genre genre) {
        List<Book>books = bookRepository.findByGenre(genre);

        List<String> booklist = new ArrayList<>();

        for (Book book : books){
            booklist.add(book.getTitle());
        }
        return booklist;
    }

    @Override
    public List<BookResponse> getBooksByPagesRange(int minPages, int maxPages) {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponseList = new ArrayList<>();
        for(Book book : books){
            if(book.getNoOfPages()>=minPages && book.getNoOfPages()<=maxPages){
                BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
                bookResponseList.add(bookResponse);
            }
        }
        return bookResponseList;
    }

    @Override
    public List<String> getAuthorsByGenre(Genre genre) {
        List<String> authors = new ArrayList<>();
        List<Author> authorList = authorRepository.findAll();
        for(Author author : authorList){
            List<Book>bookList = author.getBooks();
            for(Book book : bookList){
                if(book.getGenre().equals(genre)){
                    authors.add(author.getName());
                }
            }
        }
        return authors;
    }


}

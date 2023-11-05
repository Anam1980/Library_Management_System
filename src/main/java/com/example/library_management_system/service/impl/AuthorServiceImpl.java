package com.example.library_management_system.service.impl;

import com.example.library_management_system.dto.request.AuthorRequest;
import com.example.library_management_system.dto.response.AuthorResponse;
import com.example.library_management_system.model.Author;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.repository.AuthorRepository;
import com.example.library_management_system.service.AuthorService;
import com.example.library_management_system.transformer.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public AuthorResponse addAuthor(AuthorRequest authorRequest){

        Author saveAuthor = AuthorTransformer.AuthorRequestToAuthor(authorRequest);
        authorRepository.save(saveAuthor);
        return  AuthorTransformer.AuthorToAuthorResponse(saveAuthor);
    }

    public AuthorResponse updateEmail(int regNo, String newEmail) throws Exception {
        Optional<Author>authorOptional = authorRepository.findById(regNo);
        if(authorOptional.isPresent()){
            Author author = authorOptional.get();
            author.setEmailId(newEmail);
            authorRepository.save(author);
            return AuthorTransformer.AuthorToAuthorResponse(author);
        }
        else{
            throw new Exception("Invalid regNo");
        }


    }

    public List<String> getAuthorHavingMorethanBooks(int noOfBooks) {
        List<String> list =new ArrayList<>();
        List<Author> authorList = authorRepository.findAllByNumberBook(noOfBooks);

        for (Author author: authorList) {
            list.add(author.getName());
        }
        return  list;
    }

    @Override
    public List<String> getAuthorBooks(AuthorRequest authorRequest) {
        Author author = AuthorTransformer.AuthorRequestToAuthor(authorRequest);
        List<Book> books = author.getBooks();

        List<String> bookslist = new ArrayList<>();
        for(Book book : books){
            bookslist.add(book.getTitle());
        }

        return  bookslist;
    }
}

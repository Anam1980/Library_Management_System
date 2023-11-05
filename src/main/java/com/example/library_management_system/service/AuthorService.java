package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.AuthorRequest;
import com.example.library_management_system.dto.response.AuthorResponse;
import com.example.library_management_system.model.Book;

import java.util.List;

public interface AuthorService {
    public AuthorResponse addAuthor(AuthorRequest authorRequest);
    public AuthorResponse updateEmail(int regNo, String newEmail) throws Exception;
    List<String> getAuthorHavingMorethanBooks(int noOfBooks);
    List<String> getAuthorBooks(AuthorRequest authorRequest);
}

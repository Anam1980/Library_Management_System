package com.example.library_management_system.transformer;

import com.example.library_management_system.dto.request.AuthorRequest;
import com.example.library_management_system.dto.response.AuthorResponse;
import com.example.library_management_system.model.Author;

public class AuthorTransformer {

    public static Author AuthorRequestToAuthor(AuthorRequest authorRequest){
        return Author.builder()
                .name(authorRequest.getName())
                .age(authorRequest.getAge())
                .emailId(authorRequest.getEmailId())
                .build();
    }

    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .name(author.getName())
                .age(author.getAge())
                .emailId(author.getEmailId())
                .lastActivity(author.getLastActivity())
                .build();
    }
}

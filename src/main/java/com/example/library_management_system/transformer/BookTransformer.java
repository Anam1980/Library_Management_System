package com.example.library_management_system.transformer;

import com.example.library_management_system.dto.response.BookResponse;
import com.example.library_management_system.model.Book;

public class BookTransformer {
    public static BookResponse BookToBookResponse(Book book){
        return  BookResponse.builder()
                .authorName(book.getTitle())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .authorName(book.getAuthor().getName())
                .build();
    }
}

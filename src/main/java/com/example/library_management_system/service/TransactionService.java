package com.example.library_management_system.service;

import com.example.library_management_system.dto.response.IssueBookResponse;
import com.example.library_management_system.dto.response.ReturnBook;

public interface TransactionService {
    IssueBookResponse issueBook(int bookId, int studentId);

    ReturnBook returnBook(int bookId, int studentId);
}

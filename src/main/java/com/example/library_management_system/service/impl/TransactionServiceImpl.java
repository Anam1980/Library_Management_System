package com.example.library_management_system.service.impl;

import com.example.library_management_system.Enum.TransactionStatus;
import com.example.library_management_system.dto.response.IssueBookResponse;
import com.example.library_management_system.dto.response.ReturnBook;
import com.example.library_management_system.exception.BookNotAvailableException;
import com.example.library_management_system.exception.StudentNotFoundException;
import com.example.library_management_system.model.Book;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.model.Transaction;
import com.example.library_management_system.repository.BookRepository;
import com.example.library_management_system.repository.StudentRepository;
import com.example.library_management_system.repository.TransactionRepo;
import com.example.library_management_system.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TransactionRepo transactionRepo;

    public IssueBookResponse issueBook(int bookId, int studentId) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Invalid student id!!");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new BookNotAvailableException("Invalid book id");
        }

        Book book = optionalBook.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("Book already issued");
        }

        // issue the book
        Student student = studentOptional.get();

        // create transaction
        Transaction transaction = Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();

        Transaction savedTransaction = transactionRepo.save(transaction);

        // update book
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);

        // card changes
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book);  // book and transaction
        Student savedStudent = studentRepository.save(student);  // student and transaction

        //  send an email
        String text = "Hi! " + student.getName() + " The below book has been issued to you\n" +
                book.getTitle() + " \nThis is the transaction number: "+savedTransaction.getTransactionNumber();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("acciojob49@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        // prepare response
        return IssueBookResponse.builder()
                .bookName(savedBook.getTitle())
                .transactionStatus(savedTransaction.getTransactionStatus())
                .transactionTime(savedTransaction.getTransactionTime())
                .transactionNumber(savedTransaction.getTransactionNumber())
                .studentName(savedStudent.getName())
                .libraryCardNumber(savedStudent.getLibraryCard().getCardNo())
                .authorName(savedBook.getAuthor().getName())
                .build();

    }

    @Override
    public ReturnBook returnBook(int bookId, int studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(!studentOptional.isPresent()){
            throw new StudentNotFoundException("Invalid student id!!");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotAvailableException("Invalid book id");
        }

        Book book = optionalBook.get();
        if(!book.isIssued()){
            throw new BookNotAvailableException("Book already returned");
        }

        // Book is issued, now proceed with returning the book
        Student student = studentOptional.get();

        // Update book status and student details
        book.setIssued(false);
        bookRepository.save(book);


        // Convert LocalDate to Date for issueDate
        LocalDate localDate = student.getLibraryCard().getIssueDate().toLocalDate();
        java.sql.Date issueDate = java.sql.Date.valueOf(localDate);
        // Create a ReturnBook object to represent the return event
        ReturnBook returnBook = new ReturnBook();
        returnBook.setBookId(book.getId());
        returnBook.setStudentId(student.getRegNo());
        returnBook.setReturnDate(java.sql.Date.valueOf(LocalDate.now()));
        returnBook.setStudentName(student.getName());
        returnBook.setBookName(book.getTitle());

        return  returnBook;






    }
}

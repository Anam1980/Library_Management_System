package com.example.library_management_system.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String msg){
        super(msg);
    }
}

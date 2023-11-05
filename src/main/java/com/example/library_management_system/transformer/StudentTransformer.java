package com.example.library_management_system.transformer;

import com.example.library_management_system.dto.request.StudentRequest;
import com.example.library_management_system.dto.response.LibraryCardResponse;
import com.example.library_management_system.dto.response.StudentResponse;
import com.example.library_management_system.model.Student;

public class StudentTransformer {

    public static Student StudentRequestToStudentTransformer(StudentRequest studentRequest){
        return   Student.builder()
                .name(studentRequest.getName())
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
    }

    public static StudentResponse StudentToStudentResponse(Student student){

        LibraryCardResponse cardResponse = LibraryCardResponse.builder()
                .cardNo(student.getLibraryCard().getCardNo())
                .cardStatus(student.getLibraryCard().getCardStatus())
                .issueDate(student.getLibraryCard().getIssueDate())
                .build();

        return   StudentResponse.builder()
                .name(student.getName())
                .email(student.getEmail())
                .libraryCardResponse(cardResponse)
                .build();
    }
}

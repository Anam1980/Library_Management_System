package com.example.library_management_system.service;

import com.example.library_management_system.dto.request.StudentRequest;
import com.example.library_management_system.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse removeStudent(int regNo);

    StudentResponse updateAge(int regNo, int age) throws Exception;

    StudentResponse addStudent(StudentRequest studentRequest);

    StudentResponse getStudent(int regNo);

    List<StudentResponse> getAllStudents();

    List<String> getAllMales();
}

package com.example.library_management_system.service;

import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.dto.request.StudentRequest;
import com.example.library_management_system.dto.response.StudentResponse;
import com.example.library_management_system.model.LibraryCard;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.repository.StudentRepository;
import com.example.library_management_system.transformer.LibraryCardTransformer;
import com.example.library_management_system.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        Student student = StudentTransformer.StudentRequestToStudentTransformer(studentRequest);

        LibraryCard libraryCard = LibraryCardTransformer.PrepareCard();

        student.setLibraryCard(libraryCard);//set librarycard for student

        Student saveStudent=studentRepository.save(student);//save both student and librarycard

        //saved model to reponse dto
       return StudentTransformer.StudentToStudentResponse(saveStudent);

    }

    public Student getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        }
        return null;
    }
    public List<String> getAllMales(){

        List<String> names = new ArrayList<>();
        List<Student> students = studentRepository.findByGender(Gender.MALE);
        for(Student s : students){
            names.add(s.getName());
        }
        return names;
    }
}

package com.example.library_management_system.service.impl;

import com.example.library_management_system.Enum.Gender;
import com.example.library_management_system.dto.request.StudentRequest;
import com.example.library_management_system.dto.response.StudentResponse;
import com.example.library_management_system.model.LibraryCard;
import com.example.library_management_system.model.Student;
import com.example.library_management_system.repository.StudentRepository;
import com.example.library_management_system.service.StudentService;
import com.example.library_management_system.transformer.LibraryCardTransformer;
import com.example.library_management_system.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        // create object using builder
        Student student = StudentTransformer.StudentRequestToStudentTransformer(studentRequest);
        LibraryCard card = LibraryCardTransformer.PrepareCard();

        card.setStudent(student);
        student.setLibraryCard(card);  // set librarycard for student
        Student savedStudent = studentRepository.save(student); // save both student and library card

        // saved model to response dto
        return StudentTransformer.StudentToStudentResponse(savedStudent);

    }

    public StudentResponse getStudent(int regNo) {

        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            return StudentTransformer.StudentToStudentResponse(studentOptional.get());
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

    public StudentResponse removeStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            studentRepository.delete(student);
            return StudentTransformer.StudentToStudentResponse(student);
        }
        else{
            return null;
        }
    }

    public StudentResponse updateAge(int regNo, int age) throws Exception {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            student.setAge(age);
            studentRepository.save(student);
            return StudentTransformer.StudentToStudentResponse(student);
        }
        else {
           throw new Exception("Imvalid regNo");
        }
    }

    public List<StudentResponse> getAllStudents() {
        List<StudentResponse> students = new ArrayList<>();
        List<Student> list = studentRepository.findAll();

        for(Student student : list){
            StudentResponse studentResponse = StudentTransformer.StudentToStudentResponse(student);
            students.add(studentResponse);
        }
        return students;
    }
}

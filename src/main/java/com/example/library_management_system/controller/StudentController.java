package com.example.library_management_system.controller;

import com.example.library_management_system.dto.request.StudentRequest;
import com.example.library_management_system.dto.response.StudentResponse;
import com.example.library_management_system.service.StudentService;
import com.example.library_management_system.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        StudentResponse student = studentService.getStudent(regNo);
        if(student!=null){
            return new ResponseEntity(student,HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid id!!",HttpStatus.BAD_REQUEST);
    }

    // delete a student --> regNo
    @DeleteMapping("/delete-Student")
    public ResponseEntity removeStudent(@RequestParam("id") int regNo){

        StudentResponse studentResponse= studentService.removeStudent(regNo);
        if(studentResponse == null){
            return new ResponseEntity<>("Student with this registration not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Student deleted successfully", HttpStatus.ACCEPTED);
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update-Age/id/{regNo}/age/{age}")
    public ResponseEntity updateAge(@PathVariable("regNo") int regNo,@PathVariable("age") int age) throws Exception {
        StudentResponse studentResponse = studentService.updateAge(regNo, age);
        if(studentResponse == null){
            return new ResponseEntity<>("Student with this registration not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Id ="+regNo+", student's age updated successfully", HttpStatus.ACCEPTED);
    }

    // get all the students in the db  --> findAll()
    @GetMapping("/get-AllStudents")
    public List<StudentResponse> getAllStudents(){
        List<StudentResponse> studentList = studentService.getAllStudents();
        return  studentList;
    }

    // get list of all male students
    @GetMapping("/get-males")
    public List<String> getAllMales(){
        List<String> males = studentService.getAllMales();
        return males;
    }


}
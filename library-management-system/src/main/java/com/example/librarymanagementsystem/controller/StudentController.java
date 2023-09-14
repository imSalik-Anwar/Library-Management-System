package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseStudent;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseStudentWhenAdded;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestStudent;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.service.StudentService;
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
    public ResponseStudentWhenAdded addStudent(@RequestBody RequestStudent requestStudent){
        return studentService.addStudent(requestStudent);
    }
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        try{
            ResponseStudent responseStudent = studentService.getStudent(regNo);
            return new ResponseEntity(responseStudent, HttpStatus.FOUND);
        } catch (StudentNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // delete a student --> regNo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int regNo){
        try {
            return studentService.deleteStudent(regNo);
        } catch (StudentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestParam("id") int regNo, @RequestParam("age") int newAge){
        return studentService.updateStudent(regNo, newAge);
    }

    // get all the students in the db
    @GetMapping("/get-all")
    public List<ResponseStudent> getAllStudents(){
        return studentService.getAllStudents();
    }

    // get list of all male students
    @GetMapping("/get-all-male-students")
    public List<ResponseStudent> getAllMaleStudents(){
        return studentService.getAllMaleStudents();
    }
}

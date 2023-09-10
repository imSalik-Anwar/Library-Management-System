package com.example.librarymanagementsystem;

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
    public ResponseEntity addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        Student student = studentService.getStudent(regNo);
        if(student != null){
            return new ResponseEntity(student, HttpStatus.FOUND);
        }
        return new ResponseEntity("Invalid Id", HttpStatus.BAD_REQUEST);
    }
    // delete a student --> regNo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int regNo){
        return studentService.deleteStudent(regNo);
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update")
    public ResponseEntity updateStudent(@RequestParam("id") int regNo, @RequestParam("age") int newAge){
        return studentService.updateStudent(regNo, newAge);
    }

    // get all the students in the db

    // get list of all male students
}

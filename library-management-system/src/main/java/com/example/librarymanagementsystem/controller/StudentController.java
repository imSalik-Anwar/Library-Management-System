package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.model.Student;
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
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/get")
    public ResponseEntity<String> getStudent(@RequestParam("id") int regNo){
        Student student = studentService.getStudent(regNo);
        if(student != null){
            String res = "";
            res += "regNo: "+student.getRegNo()+"\n";
            res += "name: "+student.getName()+"\n";
            res += "age: "+student.getAge()+"\n";
            res += "email: "+student.getEmail()+"\n";
            res += "gender: "+student.getGender();
            return new ResponseEntity<>(res, HttpStatus.FOUND);
        }
        return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
    }
    // delete a student --> regNo
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int regNo){
        return studentService.deleteStudent(regNo);
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update")
    public ResponseEntity<String> updateStudent(@RequestParam("id") int regNo, @RequestParam("age") int newAge){
        return studentService.updateStudent(regNo, newAge);
    }

    // get all the students in the db
    @GetMapping("/get-all")
    public List<String> getAllStudents(){
        return studentService.getAllStudents();
    }

    // get list of all male students
    @GetMapping("/get-all-male-students")
    public List<String> getAllMaleStudents(){
        return studentService.getAllMaleStudents();
    }
}

package com.example.librarymanagementsystem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity addStudent(Student student) {
        Student obj = studentRepository.save(student);
        return new ResponseEntity(obj, HttpStatus.CREATED);
    }

    public Student getStudent(int regNo) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    public ResponseEntity deleteStudent(int regNo) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            studentRepository.deleteById(regNo);
            return new ResponseEntity("Student deleted", HttpStatus.OK);
        }
        return new ResponseEntity("Invalid Id", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity updateStudent(int regNo, int newAge) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            Student obj = student.get();
            obj.setAge(newAge);
            studentRepository.save(obj);
            return new ResponseEntity("Age updated", HttpStatus.OK);
        }
        return new ResponseEntity("Invalid Id", HttpStatus.BAD_REQUEST);
    }
}

package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String addStudent(Student student) {
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);

        student.setLibraryCard(libraryCard);
        studentRepository.save(student);
        return "Student saved";
    }

    public Student getStudent(int regNo) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            return student.get();
        }
        return null;
    }

    public ResponseEntity<String> deleteStudent(int regNo) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            studentRepository.deleteById(regNo);
            return new ResponseEntity<>("Student deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateStudent(int regNo, int newAge) {
        Optional<Student> student = studentRepository.findById(regNo);
        if(student.isPresent()){
            Student obj = student.get();
            obj.setAge(newAge);
            studentRepository.save(obj);
            return new ResponseEntity<>("Age updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
    }

    public List<String> getAllStudents() {
        List<Student> list = studentRepository.findAll();
        List<String> allStudents = new ArrayList<>();
        for(Student student : list){
            allStudents.add(student.getName());
        }
        return allStudents;
    }

    public List<String> getAllMaleStudents() {
        List<Student> allStudents = studentRepository.findAll();
        List<String> maleStudents = new ArrayList<>();
        for(Student student : allStudents){
            if(student.getGender().equals(Gender.MALE)){
                maleStudents.add(student.getName());
            }
        }
        return maleStudents;
    }
}

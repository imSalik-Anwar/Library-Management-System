package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseStudent;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseStudentWhenAdded;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestStudent;
import com.example.librarymanagementsystem.model.Student;

public class StudentConverter {

    public static Student fromReqStudentToStudent(RequestStudent requestStudent){
        return  Student.builder().
                name(requestStudent.getName()).
                age(requestStudent.getAge()).
                email(requestStudent.getEmail()).
                gender(requestStudent.getGender()).
                build();
    }

    public static ResponseStudentWhenAdded fromStudentToResponseStudentWhenAdded(Student student){
        return  ResponseStudentWhenAdded.builder().
                name(student.getName()).
                age(student.getAge()).
                gender(student.getGender()).
                build();
    }

    public static ResponseStudent fromStudentResStudent(Student student){
         return ResponseStudent.builder().
                name(student.getName()).
                age(student.getAge()).
                gender(student.getGender()).
                build();
    }
}

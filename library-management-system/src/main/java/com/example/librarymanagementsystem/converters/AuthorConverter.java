package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseAuthor;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestAuthor;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Student;

public class AuthorConverter {

    public static Author fromRequestAuthorToAuthor(RequestAuthor requestAuthor){
        return  Author.builder().
                name(requestAuthor.getName()).
                age(requestAuthor.getAge()).
                email(requestAuthor.getEmail()).
                build();
    }
    public static ResponseAuthor fromAuthorToResponseAuthor(Author author){
        return  ResponseAuthor.builder().
                name(author.getName()).
                age(author.getAge()).
                email(author.getEmail()).
                id(author.getId()).
                build();
    }
}

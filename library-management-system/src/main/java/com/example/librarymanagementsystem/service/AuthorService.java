package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(Author author) {
        Author author1 = authorRepository.save(author);
        return "Author added successfully.";
    }

    public String updateEmail(int authorId, String email) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author does not exists.");
        }
        Author author = authorOptional.get();
        author.setEmail(email);
        authorRepository.save(author);
        return "Email updated to "+author.getEmail();
    }

    public List<String> getBooksByAnAuthor(int authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author does not exists.");
        }
        Author author = authorOptional.get();
        List<String> bookList = new ArrayList<>();
        for(Book book : author.getBooks()){
            bookList.add(book.getTitle());
        }
        return bookList;
    }

    public List<String> writersWithXNumberOfBooks(int count) {
        List<String> responseList = new ArrayList<>();
        for(Author author : authorRepository.findAll()){
            if(author.getBooks().size() == count){
                responseList.add(author.getName());
            }
        }
        return responseList;
    }
}

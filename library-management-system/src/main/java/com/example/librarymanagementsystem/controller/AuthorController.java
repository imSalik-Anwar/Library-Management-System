package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    // Add author in DB
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        String response = authorService.addAuthor(author);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/update-email/{id}/{email}")
    public ResponseEntity<String> updateEmail(@PathVariable("id") int authorId, @PathVariable("email") String email){
        try{
            String response = authorService.updateEmail(authorId, email);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (AuthorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // Give me the names of all the books written by a particular author
    @GetMapping("/all-books-by-an-author")
    public ResponseEntity getBooksByAnAuthor(@RequestParam("id") int authorId){
        try{
            List<String> list = authorService.getBooksByAnAuthor(authorId);
            return new ResponseEntity(list, HttpStatus.FOUND);
        } catch (AuthorNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // give me the names of authors who have written more than 'x' number of books
    @GetMapping("/writers-with-x-number-of-books")
    public ResponseEntity writersWithXNumberOfBooks(@RequestParam("number") int count){
            List<String> list = authorService.writersWithXNumberOfBooks(count);
            if(list.isEmpty()){
                return new ResponseEntity("No Author has "+count+" books.", HttpStatus.OK);
            }
            return new ResponseEntity(list, HttpStatus.FOUND);
    }
}

package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseTransaction;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue-book")
    public ResponseEntity issueBook(@RequestParam("bookId") int bookId, @RequestParam("studentId") int studentId){
        try{
            ResponseTransaction responseTransaction = transactionService.issueBook(bookId, studentId);
            return new ResponseEntity(responseTransaction, HttpStatus.CREATED);
        } catch (StudentNotFoundException | BookNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

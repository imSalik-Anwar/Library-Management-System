package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseTransaction;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.converters.TransactionConverter;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    public ResponseTransaction issueBook(int bookId, int studentId) {
        // check if student and book exist
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("Student does not exists!");
        }
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException(("Book Does not exist!"));
        }
        // If student and book exists, get them
        Student student = studentOptional.get();
        Book book = bookOptional.get();
        // check if book is already issued
        if(book.isIssued()){
            throw new BookNotFoundException(("Sorry, book is already issued to some other student!"));
        }
        // if everything is fine so far, do transaction
        Transaction transaction = Transaction.builder().
                transactionNumber(String.valueOf(UUID.randomUUID())).
                transactionStatus(TransactionStatus.SUCCESS).
                book(book).
                libraryCard(student.getLibraryCard()).
                build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        // update book
        book.getTransactions().add(savedTransaction);
        book.setIssued(true);
        // update card
        student.getLibraryCard().getTransactions().add(savedTransaction);

        Book savedBook = bookRepository.save(book); // saves book and transaction
        Student savedStudent = studentRepository.save(student); // saves book and transaction

        return TransactionConverter.fromTransactionToResponseTransaction(savedTransaction); // return transaction response DTO
    }
}

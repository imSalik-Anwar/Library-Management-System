package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseTransaction;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.converters.TransactionConverter;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.exception.TransactionNotFoundException;
import com.example.librarymanagementsystem.mail.MailComposer;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.LibraryCard;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    JavaMailSender javaMailSender;
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

        // send email
        SimpleMailMessage message = MailComposer.composeIssueBookEmail(savedBook, savedStudent, savedTransaction);
        javaMailSender.send(message);

        return TransactionConverter.fromTransactionToResponseTransaction(savedTransaction); // return transaction response DTO
    }

    public void releaseBook(int transactionId) {
        // check if transaction exists
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isEmpty()){
            throw new TransactionNotFoundException("Invalid transaction Id!");
        }
        // get transaction
        Transaction transaction = transactionOptional.get();
        // get corresponding book and library card
        Book book = transaction.getBook();
        LibraryCard libraryCard = transaction.getLibraryCard();
        // set book free
        book.setIssued(false);
        // remove transaction from its parent tables i.e. book and library card
        book.getTransactions().remove(transaction);
        libraryCard.getTransactions().remove(transaction);
        // remove transaction from transaction DB
        transactionRepository.delete(transaction);
        // save parent entities to update them
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(libraryCard.getStudent());

        // send email
        SimpleMailMessage message = MailComposer.sendReleaseBookEmail(savedStudent, savedBook, transaction);
        javaMailSender.send(message);
    }
}

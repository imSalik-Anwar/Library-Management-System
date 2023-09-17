package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseAuthor;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook_AuthorAndGenre;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestAuthor;
import com.example.librarymanagementsystem.converters.AuthorConverter;
import com.example.librarymanagementsystem.converters.BookConverter;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ResponseAuthor addAuthor(RequestAuthor requestAuthor) {
        // create an author and set all the values coming from requestAuthor DTO
        Author author = AuthorConverter.fromRequestAuthorToAuthor(requestAuthor);
        // save the author to generate id
        Author authorObj = authorRepository.save(author);
        // create a responseAuthor DTO and set the returning values by taking from Author
        ResponseAuthor responseAuthor = AuthorConverter.fromAuthorToResponseAuthor(authorObj);
        return responseAuthor;

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

    public List<ResponseBook_AuthorAndGenre> getBooksByAnAuthor(int authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author does not exists.");
        }
        Author author = authorOptional.get();
        List<ResponseBook_AuthorAndGenre> bookList = new ArrayList<>();
        for(Book book : author.getBooks()){
            ResponseBook_AuthorAndGenre responseBook_authorAndGenre = BookConverter.fromBookToResBook_AuthorAndGenre(book);
            bookList.add(responseBook_authorAndGenre);
        }
        return bookList;
    }

    public List<ResponseAuthor> writersWithXNumberOfBooks(int count) {
        List<ResponseAuthor> responseList = new ArrayList<>();
        for(Author author : authorRepository.findAll()){
            if(author.getBooks().size() == count){
                ResponseAuthor responseAuthor = AuthorConverter.fromAuthorToResponseAuthor(author);
                responseList.add(responseAuthor);
            }
        }
        return responseList;
    }
}

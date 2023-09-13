package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseAuthor;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestAuthor;
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

    public ResponseAuthor addAuthor(RequestAuthor requestAuthor) {
        // create an author and set all the values coming from requestAuthor DTO
        Author author = new Author();
        author.setName(requestAuthor.getName());
        author.setAge(requestAuthor.getAge());
        author.setEmail(requestAuthor.getEmail());
        // save the author
        Author authorObj = authorRepository.save(author);
        // create a responseAuthor DTO and set the returning values by taking from Author
        ResponseAuthor responseAuthor = new ResponseAuthor();
        responseAuthor.setName(authorObj.getName());
        responseAuthor.setAge(authorObj.getAge());
        responseAuthor.setEmail(authorObj.getEmail());
        responseAuthor.setId(authorObj.getId());
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

    public List<ResponseAuthor> writersWithXNumberOfBooks(int count) {
        List<ResponseAuthor> responseList = new ArrayList<>();
        for(Author author : authorRepository.findAll()){
            if(author.getBooks().size() == count){
                ResponseAuthor responseAuthor = new ResponseAuthor();
                responseAuthor.setId(author.getId());
                responseAuthor.setName(author.getName());
                responseAuthor.setAge(author.getAge());
                responseAuthor.setEmail(author.getEmail());
                responseList.add(responseAuthor);
            }
        }
        return responseList;
    }
}

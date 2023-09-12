package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    public String addBook(Book book) {
        // check if author exists or not (we will pass author Id in the book object)
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author does not exists.");
        }
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBooks().add(book);
        authorRepository.save(author);
        return "Book added successfully";
    }

    public String deleteABook(String title) {
        boolean bookDeleted = false;
        for(Author author : authorRepository.findAll()){
            List<Book> bookList = author.getBooks();
            for(Book book : bookList){
                if(book.getTitle().equals(title)){
                    bookList.remove(book);
                    bookRepository.delete(book);
                    author.setBooks(bookList);
                    authorRepository.save(author);
                    bookDeleted = true;
                    break;
                }
            }
            if(bookDeleted){
                break;
            }
        }
        if(!bookDeleted){
            throw new BookNotFoundException("Book does not exists.");
        }
        return "Book deleted successfully.";
    }

    public List<String> bookFromAGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> responseList = new ArrayList<>();
        for(Book book : bookList){
            responseList.add(book.getTitle());
        }
        return responseList;
    }

    public List<String> bookFromAGenreCosting500(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> responseList = new ArrayList<>();
        for(Book book : bookList){
            if(book.getCost() == 500) {
                responseList.add(book.getTitle());
            }
        }
        return responseList;
    }

    public List<String> booksHavingPagesAtoB(int minPage, int maxPage) {
        List<Book> bookList = bookRepository.findAll();
        List<String> responseList = new ArrayList<>();
        for(Book book : bookList){
            if(book.getNoOfPages() >= minPage && book.getNoOfPages() <= maxPage) {
                responseList.add(book.getTitle());
            }
        }
        return responseList;
    }

    public List<String> authorsFromAGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> responseList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for(Book book : bookList){
            set.add(book.getAuthor().getName());
        }
        for(String name : set){
            responseList.add(name);
        }
        return responseList;
    }
}

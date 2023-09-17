package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseAuthor;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook_AuthorAndGenre;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestBook;
import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.converters.AuthorConverter;
import com.example.librarymanagementsystem.converters.BookConverter;
import com.example.librarymanagementsystem.converters.StudentConverter;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Author;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    public ResponseBook addBook(RequestBook requestBook) {
        // check if author exists or not (we will pass author Id in the requestBook DTO)
        Optional<Author> authorOptional = authorRepository.findById(requestBook.getAuthorId());
        if(!authorOptional.isPresent()){
            throw new AuthorNotFoundException("Author does not exists.");
        }
        Author author = authorOptional.get();
        Book book = Book.builder().
                author(author).
                title(requestBook.getTitle()).
                genre(requestBook.getGenre()).
                cost(requestBook.getCost()).
                noOfPages(requestBook.getNoOfPages()).
                build();
        author.getBooks().add(book);
        authorRepository.save(author);

        ResponseBook responseBook = BookConverter.fromBookToResBook(book);
//                ResponseBook.builder().
//                cost(book.getCost()).
//                noOfPages(book.getNoOfPages()).
//                title(book.getTitle()).
//                genre(book.getGenre()).
//                authorName(author.getName()).
//                build();

        return responseBook;

    }

    public String deleteABook(String title) {
        Optional<Book> bookOptional = Optional.ofNullable(bookRepository.findByTitle(title));
        if(!bookOptional.isPresent()){
            throw new BookNotFoundException("Book does not exists.");
        }
        bookRepository.delete(bookOptional.get());
        return "Book deleted successfully.";
    }

    public List<ResponseBook_AuthorAndGenre> bookFromAGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<ResponseBook_AuthorAndGenre> responseList = new ArrayList<>();
        for(Book book : bookList){
            ResponseBook_AuthorAndGenre responseBookAuthorAndGenre = BookConverter.fromBookToResBook_AuthorAndGenre(book);
            responseList.add(responseBookAuthorAndGenre);
        }
        return responseList;
    }

    public List<ResponseBook_AuthorAndGenre> bookFromAGenreCosting500(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<ResponseBook_AuthorAndGenre> responseList = new ArrayList<>();
        for(Book book : bookList){
            if(book.getCost() == 500) {
                ResponseBook_AuthorAndGenre responseBookAuthorAndGenre = BookConverter.fromBookToResBook_AuthorAndGenre(book);
                responseList.add(responseBookAuthorAndGenre);
            }
        }
        return responseList;
    }

    public List<ResponseBook_AuthorAndGenre> booksHavingPagesAtoB(int minPage, int maxPage) {
        List<Book> bookList = bookRepository.findBookHavingPagesBetweenAandB(minPage, maxPage);
        List<ResponseBook_AuthorAndGenre> responseList = new ArrayList<>();
        for(Book book : bookList){
            ResponseBook_AuthorAndGenre responseBookAuthorAndGenre = BookConverter.fromBookToResBook_AuthorAndGenre(book);
            responseList.add(responseBookAuthorAndGenre);
        }
        return responseList;
    }

    public List<ResponseAuthor> authorsFromAGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<ResponseAuthor> responseList = new ArrayList<>();
        Set<Author> set = new HashSet<>();
        for(Book book : bookList){
            set.add(book.getAuthor());
        }
        for(Author author : set){
            ResponseAuthor responseAuthor = AuthorConverter.fromAuthorToResponseAuthor(author);
            responseList.add(responseAuthor);
        }
        return responseList;
    }
}

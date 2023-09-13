package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseAuthor;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook;
import com.example.librarymanagementsystem.DTO.resquestDTO.RequestBook;
import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    //add book
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody RequestBook requestBook){
        try{
            ResponseBook response = bookService.addBook(requestBook);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (AuthorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // delete a book
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<String> deleteABook(@PathVariable("title") String title){
        try{
            String response = bookService.deleteABook(title);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (BookNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    // give me names of all the books of a particular genre
    @GetMapping("/books-from-particular-genre")
    public ResponseEntity bookFromAGenre(@RequestParam("genre")Genre genre){
        List<String> bookList = bookService.bookFromAGenre(genre);
        if(bookList.isEmpty()){
            return new ResponseEntity("No Books found under "+genre, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookList, HttpStatus.FOUND);
    }

    // give me names of all the books of a particular genre and cost greater than 500 rs
    @GetMapping("/books-from-particular-genre-costing-500")
    public ResponseEntity bookFromAGenreCosting500(@RequestParam("genre")Genre genre){
        List<String> bookList = bookService.bookFromAGenreCosting500(genre);
        if(bookList.isEmpty()){
            return new ResponseEntity("No Books found under "+genre+" for 500 Rs.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookList, HttpStatus.FOUND);
    }

    // give me all the books having number of pages between 'a' and 'b'
    @GetMapping("/books-having-pages-between-a-to-b")
    public ResponseEntity booksHavingPagesAtoB(@RequestParam("min")int minPage, @RequestParam("max") int maxPage){
        List<String> bookList = bookService.booksHavingPagesAtoB(minPage, maxPage);
        if(bookList.isEmpty()){
            return new ResponseEntity("No Books found having pages between "+minPage+" and "+maxPage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookList, HttpStatus.FOUND);
    }
    // give me the names of all the authors who write a particular genre
    @GetMapping("/authors-from-a-particular-genre/{genre}")
    public ResponseEntity authorsFromAGenre(@PathVariable("genre") Genre genre){
        List<ResponseAuthor> authorList = bookService.authorsFromAGenre(genre);
        if(authorList.isEmpty()){
            return new ResponseEntity("No Author found under "+genre, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(authorList, HttpStatus.FOUND);
    }
}

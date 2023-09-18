package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook;
import com.example.librarymanagementsystem.DTO.responseDTO.ResponseBook_AuthorAndGenre;
import com.example.librarymanagementsystem.model.Book;

public class BookConverter {
    public static ResponseBook_AuthorAndGenre fromBookToResBook_AuthorAndGenre(Book book){
        return  ResponseBook_AuthorAndGenre.builder().
                title(book.getTitle()).
                genre(book.getGenre()).
                authorName(book.getAuthor().getName()).
                build();
    }

    public static ResponseBook fromBookToResBook(Book book){
        return  ResponseBook.builder().
                cost(book.getCost()).
                noOfPages(book.getNoOfPages()).
                title(book.getTitle()).
                genre(book.getGenre()).
                authorName(book.getAuthor().getName()).
                build();
    }

}

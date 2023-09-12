package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);

    List<Book> findByGenre(Genre genre);

//    List<Book> findByNoOfPages(int minPage, int maxPage);
}

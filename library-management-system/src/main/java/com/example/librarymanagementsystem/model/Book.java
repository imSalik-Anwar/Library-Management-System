package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String title;

    int noOfPages;

    @Enumerated(EnumType.STRING)
    Genre genre;

    double cost;

    @ManyToOne
    @JoinColumn
    Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>(); // when new book will be added, it will initially have
    // no transactions so we initialize an empty arraylist for transactions initially.
}

package com.example.librarymanagementsystem.DTO.resquestDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.model.Author;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestBook {
    int authorId; // to check whether the author exists in DB or not

    String title;

    int noOfPages;

    Genre genre;

    double cost;

}

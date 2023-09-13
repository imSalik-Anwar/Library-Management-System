package com.example.librarymanagementsystem.DTO.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseBook {
    String title;

    int noOfPages;

    Genre genre;

    double cost;
}

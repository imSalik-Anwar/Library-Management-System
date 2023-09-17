package com.example.librarymanagementsystem.DTO.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBook{

    String title;

    int noOfPages;

    double cost;

    String authorName;

    Genre genre;
}

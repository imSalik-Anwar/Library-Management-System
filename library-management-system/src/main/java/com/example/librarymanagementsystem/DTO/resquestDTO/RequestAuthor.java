package com.example.librarymanagementsystem.DTO.resquestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestAuthor {
    String name;

    int age;

    String email;
}

package com.example.librarymanagementsystem.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuthor {
    int id;

    String name;

    int age;

    String email;
}

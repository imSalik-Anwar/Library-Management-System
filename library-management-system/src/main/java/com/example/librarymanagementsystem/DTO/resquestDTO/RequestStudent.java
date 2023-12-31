package com.example.librarymanagementsystem.DTO.resquestDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestStudent {
    String name;

    int age;

    String email;

    Gender gender;
}

package com.example.librarymanagementsystem.DTO.responseDTO;

import com.example.librarymanagementsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseStudent {
    String name;

    int age;

    Gender gender;
}

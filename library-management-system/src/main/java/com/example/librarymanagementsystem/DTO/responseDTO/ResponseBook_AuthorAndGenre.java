package com.example.librarymanagementsystem.DTO.responseDTO;

import com.example.librarymanagementsystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseBook_AuthorAndGenre {
    String title;

    String authorName;

    Genre genre;
}

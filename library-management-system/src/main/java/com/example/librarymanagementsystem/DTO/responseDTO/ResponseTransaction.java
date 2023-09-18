package com.example.librarymanagementsystem.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseTransaction {

    int transactionId;

    String studentName;

    String bookTitle;

    Date issueDate;

    String authorName;

}

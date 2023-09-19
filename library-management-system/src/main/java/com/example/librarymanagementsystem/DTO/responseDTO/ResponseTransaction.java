package com.example.librarymanagementsystem.DTO.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    Date issueDate;

    String authorName;

}

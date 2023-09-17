package com.example.librarymanagementsystem.DTO.responseDTO;

import com.example.librarymanagementsystem.Enum.CardStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseLibraryCard {
    String cardNo;

    CardStatus cardStatus;

    Date issueDate;
}

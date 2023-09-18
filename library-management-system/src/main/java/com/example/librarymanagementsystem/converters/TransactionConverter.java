package com.example.librarymanagementsystem.converters;

import com.example.librarymanagementsystem.DTO.responseDTO.ResponseTransaction;
import com.example.librarymanagementsystem.model.Transaction;
public class TransactionConverter {

    public static ResponseTransaction fromTransactionToResponseTransaction(Transaction transaction){
          return ResponseTransaction.builder().
                 transactionId(transaction.getId()).
                 issueDate(transaction.getTransactionDate()).
                 studentName(transaction.getLibraryCard().getStudent().getName()).
                 bookTitle(transaction.getBook().getTitle()).
                 authorName(transaction.getBook().getAuthor().getName()).
                 build();
    }
}

package com.example.librarymanagementsystem.mail;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.model.Student;
import com.example.librarymanagementsystem.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailComposer {
    public static SimpleMailMessage composeIssueBookEmail(Book book, Student student, Transaction transaction){
        // send email
        String text = "Dear "+student.getName()+",\n"
                +"\n"
                +"We have successfully processed your request for issuing \""+book.getTitle()+"\" by "+book.getAuthor().getName()+".\n"
                +"Following are the details of your request: \n"
                +"\n"
                +"Transaction Id: "+transaction.getId()+"\n"
                +"Book Title: "+book.getTitle()+"\n"
                +"Author: "+book.getAuthor().getName()+"\n"
                +"Genre: "+book.getGenre()+"\n"
                +"Library Card No: "+transaction.getTransactionNumber()+"\n"
                +"Date: "+transaction.getTransactionDate()+"\n"
                +"\n"
                +"Happy reading!"
                +"\n"
                +"\n"
                +"Note: Please keep your transaction Id handy as it is required at the time of releasing the book.";


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lms.studentservice@gmail.com");
        message.setTo(student.getEmail());
        message.setSubject("Book Issue Request Processed!");
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage sendReleaseBookEmail(Student student, Book book, Transaction transaction) {
        String text = "Dear "+student.getName()+", \n"
                +"\n"
                +"Your book release request for \""+book.getTitle()+"\" "+"(transaction Id: "+transaction.getId()+") has been processed. We hope to see you soon " +
                "for your great next read."
                +"\n"
                +"\n"
                +"Happy Reading!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lms.studentservice@gmail.com");
        message.setTo(student.getEmail());
        message.setSubject("Book Release Request Processed!");
        message.setText(text);

        return message;
    }

    public static SimpleMailMessage composeAddStudentEmail(Student student) {
        String text = "Dear "+student.getName()+",\n"
                +"\n"
                +"Your application for Spring Library membership has been accepted, and we are delighted to have you abroad. "
                +"Following are your Spring Library membership details: \n"
                +"\n"
                +"Student Name: "+student.getName()+"\n"
                +"Age: "+student.getAge()+"\n"
                +"Gender: "+student.getGender()+"\n"
                +"Library Card No: "+student.getLibraryCard().getId()+"\n"
                +"Card Status: "+student.getLibraryCard().getCardStatus()+"\n"
                +"Card Issue Date: "+student.getLibraryCard().getIssueDate()+"\n"
                +"\n"
                +"\n"
                +"Some More Words from Spring Library: \n"
                +"\n"
                +"We, at Spring Library, value our principles above anything. Because we believe that a library is a place where "
                +"a society's more sensible members belong to. To live up to this belief, we expect our members to stick "
                +"to our core values, and we urge you to go through the following read for the same: \n"
                +"\n"
                +"1. Our regular hours are 9:00AM to 6:00PM, with holiday variations.\n"
                +"2. You can borrow up to 6 books for 25 days each. Renewals are possible.\n"
                +"3. Fines are 5₹ per day for overdue books.\n"
                +"4. Please maintain a quiet environment for all library users.\n"
                +"5. Explore our online resources and e-books on our website.\n"
                +"6. Join our workshops and events. Details on our notice board and website.\n"
                +"7. For questions or assistance, contact us at [lms.studentservice@gmail.com] or [9211].\n"
                +"\n"
                +"We're here to make your library experience amazing. Enjoy your time at Spring Library!\n"
                +"\n"
                +"Best regards!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("lms.studentservice@gmail.com");
        message.setTo(student.getEmail());
        message.setSubject("Welcome Onboard!");
        message.setText(text);

        return message;

    }
}

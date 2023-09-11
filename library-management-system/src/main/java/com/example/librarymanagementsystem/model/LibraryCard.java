package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "library_card")
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardNo;
    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;
    @CreationTimestamp
    Date IssueDate;

    @OneToOne // tells hibernate what is the mapping cardinality between entities i.e. one library card can be owned by one student only
    @JoinColumn // creates a column for Foreign key in child table i.e. Library Card. Chooses the PK of the parent table (Student) as FK by default
    Student student; // this is the entity to which FK will refer from the child table
    // Note: @JoinColumn(name = "employee_email_id", referencedColumnName = "email") to set FK column name manually and to reference FK to some other
    // column than PK in parent table.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date issueDate) {
        IssueDate = issueDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

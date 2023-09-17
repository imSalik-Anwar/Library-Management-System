package com.example.librarymanagementsystem.model;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE) // Rather than writing private before every attribute, we use this to set access of all attributes at once
@NoArgsConstructor // generates no argument constructors
@AllArgsConstructor // generates all argument constructors
@Getter // generates getter functions for all attributes
@Setter // generates setter functions for all attributes
@Entity // tells Hibernates that this is a model class and will be stored in database as an entity set and the objects of this class as entities
@Table(name = "student_info") // names the tables in the database. If not used, Hibernate will name table as the class name i.e. Student
// we should never change table name midway using @Table because it creates a new table rather than renaming existing table.
// however, we can change the name of existing table by performing Alter command on DB rather using Hibernate.
@Builder // for using lombok feature to build objects of respective class
public class Student {
    @Id // tells Hibernate that this attribute is the Primary key and can not be null or duplicate
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Implements AUTO INCREMENT on primary key
    int regNo;

    @Column(name = "student_name") // names the column in the database. If not used, Hibernate will name column as the attribute name i.e. name
    // we should never change column name midway using @Column because it creates a new column rather than renaming existing column.
    // however, we can change the name of existing column by performing Alter command on DB rather using Hibernate.
    String name;

    int age;

    @Column(unique = true, nullable = false) // sets a column as unique and not null
    String email;

    @Enumerated(EnumType.STRING) // Hibernate by default stores Enum values in DB as tinyint starting from 0. Changing EnumType to String using
    // @Enumerated annotation tells Hibernate to store enum values as String i.e. MALE and FEMALE, and not as 0 and 1.
    Gender gender;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard libraryCard;
    /* tells hibernate what is the mapping cardinality between entities i.e. one student can have only one library card.
    mappedBy holds the value to which this student object will relate in the child table i.e. Library Card
    cascade = CascadeType.ALL cascades all the operations done to the parent on to the child. It means, any CRUD operation on student will affect
    its corresponding entry in library card table.
    */
}

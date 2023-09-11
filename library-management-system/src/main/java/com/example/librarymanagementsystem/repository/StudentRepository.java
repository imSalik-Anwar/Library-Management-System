package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    /*  1. We create repository as interface.
        2. We do so because our repository has to use the functions provided by JPA Repository interface.
        3. If we make  our repository as a class, we will have to implement the functions of JPA Repository. But we don't need to do that
        because Hibernate has already done that.
        4. However, if someone wants to implement JPA Repository functions by himself, he can make Repository as a class and define implementations
        by himself. That class will be his own created ORM.
     */
}

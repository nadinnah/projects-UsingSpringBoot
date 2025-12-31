package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> { //id is of type long
    //where we fetch data
    //the JpaRepository takes care of all the CRUD operations

}

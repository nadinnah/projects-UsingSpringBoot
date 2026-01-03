package com.example.demo.services;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepo;

    public Student createStudent(Student student){
        return studentRepo.save(student);
    }

    public Student getStudentById(Long id){
        return studentRepo.findById(id).orElseThrow(()->new EntityNotFoundException("student not found"));
    }

    public void removeStudentById(Long id){
        Student student= getStudentById(id);
        studentRepo.delete(student);
    }

    public List<Student> fetchStudents(){
        return studentRepo.findAll();
    }
}

package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.Student;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepo;

    @Autowired
    StudentRepository studentRepo;

    public Course createCourse(Course course){
        return courseRepo.save(course);
    }

    public Course getCourse(String id){
      return courseRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Course not found"));
    }

    public void removeCourse(String id){
        Course course= getCourse(id);
        courseRepo.delete(course);
    }

    public List<Course> fetchCourses(){
        return courseRepo.findAll();
    }

    public Course assignStudentToCourse(Long studentId, String courseId){

        Course course= courseRepo.findById(courseId).orElseThrow(()->new RuntimeException("Course not found."));
        Student student= studentRepo.findById(studentId).orElseThrow(()->new RuntimeException("Student not found."));

        if(!course.getStudents().contains(student)){
            course.getStudents().add(student);
        }
        if(!student.getCourses().contains(course)){
            student.getCourses().add(course);
        }

        return courseRepo.save(course);
    }

}

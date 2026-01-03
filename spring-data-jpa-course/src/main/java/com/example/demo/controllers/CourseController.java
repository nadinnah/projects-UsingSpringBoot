package com.example.demo.controllers;
import com.example.demo.models.Course;
import com.example.demo.models.Student;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    public CourseService courseService;

    public CourseController(CourseService courseService) {//constructor injection
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.fetchCourses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourseById(@PathVariable String id){
        courseService.removeCourseById(id);
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public Course addStudentToCourse(@PathVariable Long studentId, @PathVariable String courseId){
    return courseService.assignStudentToCourse(studentId,courseId);
    }
}

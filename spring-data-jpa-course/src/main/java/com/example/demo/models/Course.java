package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name= "Course")
@Table(name="Course")
public class Course {

    @Id
    @Column(
            name="id",
            columnDefinition = "TEXT"
    )
    private String id;

    @Column(
            name = "name",
            nullable= false,
            columnDefinition = "TEXT"

    )
    private String name;

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Course(String id, String name) {
        this.id = id;
        this.name = name;

    }

    protected Course(){
        //no-args constructor
    }

    @ManyToMany
    @JoinTable(
            name="course_student",
            joinColumns= @JoinColumn(name="course_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    private List<Student> students;
}

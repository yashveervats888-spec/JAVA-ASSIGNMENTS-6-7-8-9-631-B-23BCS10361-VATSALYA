package com.example.springdemo;

public class Student {
    private Course course;

    // Constructor-based Dependency Injection
    public Student(Course course) {
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("Student is enrolled in: " + course.getCourseName());
    }
}

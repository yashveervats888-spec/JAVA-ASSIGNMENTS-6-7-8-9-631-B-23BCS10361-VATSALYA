package com.sms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private double balance;

    // Getters & Setters
}

package com.example.hibernate;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class StudentCRUD {
    private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                                                              .addAnnotatedClass(Student.class)
                                                              .buildSessionFactory();

    public static void main(String[] args) {
        createStudent("Ankit", "CSE", 89);
        readStudents();
        updateStudent(1, 95);
        deleteStudent(1);
        factory.close();
    }

    static void createStudent(String name, String course, double marks) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Student s = new Student();
        s.setName(name);
        s.setCourse(course);
        s.setMarks(marks);

        session.save(s);
        tx.commit();
        session.close();

        System.out.println("Student added successfully!");
    }

    static void readStudents() {
        Session session = factory.openSession();
        session.createQuery("from Student", Student.class).list()
                .forEach(s -> System.out.println(s.getId() + " " + s.getName() + " " + s.getCourse() + " " + s.getMarks()));
        session.close();
    }

    static void updateStudent(int id, double marks) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        if (s != null) {
            s.setMarks(marks);
            session.update(s);
            tx.commit();
            System.out.println("Student updated!");
        }
        session.close();
    }

    static void deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        Student s = session.get(Student.class, id);
        if (s != null) {
            session.delete(s);
            tx.commit();
            System.out.println("Student deleted!");
        }
        session.close();
    }
}

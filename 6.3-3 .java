package com.mvc;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            StudentDAO dao = new StudentDAO();

            while (true) {
                System.out.println("\n=== STUDENT MANAGEMENT MENU ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Department: ");
                        String dept = sc.next();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();

                        Student s = new Student(id, name, dept, marks);
                        dao.addStudent(s);
                    }
                    case 2 -> dao.getAllStudents().forEach(
                        s -> System.out.println(s.getStudentID() + "\t" + s.getName() + "\t" + s.getDepartment() + "\t" + s.getMarks()));
                    case 3 -> {
                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter New Marks: ");
                        double marks = sc.nextDouble();
                        dao.updateStudent(id, marks);
                    }
                    case 4 -> {
                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();
                        dao.deleteStudent(id);
                    }
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

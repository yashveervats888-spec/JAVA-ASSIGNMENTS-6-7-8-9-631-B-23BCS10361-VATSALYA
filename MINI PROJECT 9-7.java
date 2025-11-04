CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE courses (
  course_id INT AUTO_INCREMENT PRIMARY KEY,
  course_name VARCHAR(100),
  duration INT
);

CREATE TABLE students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  course_id INT,
  balance DOUBLE,
  FOREIGN KEY (course_id) REFERENCES courses(course_id)
);

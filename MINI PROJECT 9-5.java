package com.sms.service;

import com.sms.model.Student;
import com.sms.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeService {
    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void makePayment(int studentId, double amount) {
        Student student = studentDAO.getStudent(studentId);
        if (student != null) {
            student.setBalance(student.getBalance() - amount);
            studentDAO.updateStudent(student);
            System.out.println("Payment successful!");
        }
    }

    @Transactional
    public void refundPayment(int studentId, double amount) {
        Student student = studentDAO.getStudent(studentId);
        if (student != null) {
            student.setBalance(student.getBalance() + amount);
            studentDAO.updateStudent(student);
            System.out.println("Refund successful!");
        }
    }
}

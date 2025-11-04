package com.sms.dao;

import com.sms.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    public Student getStudent(int id) {
        return sessionFactory.getCurrentSession().get(Student.class, id);
    }

    public List<Student> getAllStudents() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Student", Student.class)
                .list();
    }

    public void updateStudent(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    public void deleteStudent(int id) {
        Student student = getStudent(id);
        if (student != null)
            sessionFactory.getCurrentSession().delete(student);
    }
}

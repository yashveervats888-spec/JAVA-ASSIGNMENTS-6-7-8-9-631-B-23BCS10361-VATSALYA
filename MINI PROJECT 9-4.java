
package com.sms.main;

import com.sms.config.AppConfig;
import com.sms.model.Student;
import com.sms.model.Course;
import com.sms.service.FeeService;
import com.sms.dao.StudentDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO dao = context.getBean(StudentDAO.class);
        FeeService feeService = context.getBean(FeeService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Pay Fees");
            System.out.println("4. Refund");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    Student s = new Student();
                    System.out.print("Enter name: ");
                    s.setName(sc.next());
                    s.setBalance(10000); // default balance
                    dao.saveStudent(s);
                    System.out.println("Student added!");
                    break;

                case 2:
                    dao.getAllStudents().forEach(st ->
                            System.out.println(st.getStudentId() + " | " + st.getName() + " | " + st.getBalance()));
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    feeService.makePayment(id, amt);
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter refund amount: ");
                    double refund = sc.nextDouble();
                    feeService.refundPayment(sid, refund);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    context.close();
                    System.exit(0);
            }
        }
    }
}

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.sql.*;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {
    private final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private final String DB_USER = "root";
    private final String DB_PASS = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Attendance VALUES (?, ?, ?)");
            ps.setString(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();

            out.println("<h3>Attendance Recorded Successfully!</h3>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

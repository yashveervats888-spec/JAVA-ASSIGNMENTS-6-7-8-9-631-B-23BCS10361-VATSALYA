import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";  // Replace with your DB name
        String user = "root";
        String pass = "password";

        try {
            // Step 1: Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // Step 3: Create Statement
            Statement stmt = con.createStatement();

            // Step 4: Execute Query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            // Step 5: Display Results
            System.out.println("EmpID\tName\t\tSalary");
            System.out.println("----------------------------------");

            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(id + "\t" + name + "\t\t" + salary);
            }

            // Step 6: Close Connection
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

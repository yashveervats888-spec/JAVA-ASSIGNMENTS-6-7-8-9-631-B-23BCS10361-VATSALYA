import java.sql.*;
import java.util.*;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            Scanner sc = new Scanner(System.in);
            con.setAutoCommit(false); // Enable transaction control

            while (true) {
                System.out.println("\n=== PRODUCT MENU ===");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int ch = sc.nextInt();

                switch (ch) {
                    case 1 -> addProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> {
                        con.close();
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid Choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) {
        try {
            String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Product Name: ");
            String name = sc.next();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);

            ps.executeUpdate();
            con.commit();
            System.out.println("✅ Product Added Successfully!");
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            System.out.println("❌ Error adding product: " + e.getMessage());
        }
    }

    static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

        System.out.println("\nProductID\tProductName\tPrice\tQuantity");
        System.out.println("-----------------------------------------------");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t" +
                               rs.getDouble(3) + "\t" + rs.getInt(4));
        }
    }

    static void updateProduct(Connection con, Scanner sc) {
        try {
            String sql = "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Product ID: ");
            int id = sc.nextInt();
            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter New Quantity: ");
            int qty = sc.nextInt();

            ps.setDouble(1, price);
            ps.setInt(2, qty);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product Updated Successfully!");
            } else {
                System.out.println("❌ Product Not Found!");
            }
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            System.out.println("❌ Error updating product: " + e.getMessage());
        }
    }

    static void deleteProduct(Connection con, Scanner sc) {
        try {
            String sql = "DELETE FROM Product WHERE ProductID=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Product ID to delete: ");
            int id = sc.nextInt();

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product Deleted Successfully!");
            } else {
                System.out.println("❌ Product Not Found!");
            }
        } catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            System.out.println("❌ Error deleting product: " + e.getMessage());
        }
    }
}

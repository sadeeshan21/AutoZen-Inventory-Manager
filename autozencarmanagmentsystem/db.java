package autozencarmanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {
    public static Connection myccon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is available
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sdp", // database name name
                "",                            // database username
                ""                                 // database password
            );
            return c;
        } catch (Exception e) {
            // Show or print the exact error
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace(); //  debugging
            return null;
        }
    }
}

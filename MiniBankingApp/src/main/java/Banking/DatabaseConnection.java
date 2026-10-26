package Banking;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection con;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/BANK";
            String user = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e) {
            System.out.println(
                    "Connection Failed! " + e.getMessage()
            );
        }

        return con;
    }
}

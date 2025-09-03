package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Huy04022005***";

    private ConnectionMySQL() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found!");
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            System.err.println("Database connection failed!");
            throw new RuntimeException(e);
        }
    }
}

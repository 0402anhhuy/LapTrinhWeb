package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class DBConnection {
    public static String url = "jdbc:mysql://localhost:3306/test";
    public static String username = "root";
    public static String password = "Huy04022005***";

    public static Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to load driver class.");
            throw new RuntimeException(e);
        }
        catch (SQLException e) {
            System.out.println("Error: Unable to connect to database.");
            throw new RuntimeException(e);
        }
        return connection;
    }
}

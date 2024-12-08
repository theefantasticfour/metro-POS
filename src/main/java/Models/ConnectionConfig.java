package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    // Static variable to hold the single instance of the connection
    private static Connection connection = null;

    // Database credentials

   /*private static final String DB_URL = "jdbc:mysql://srv1553.hstgr.io:3306/u628307016_DanishDB?autoReconnect=true";
    private static final String DB_USER = "u628307016_user" ;
    private static final String DB_PASSWORD = "!@#Nm123456!@#";*/
    private static final String DB_URL = "jdbc:mysql://localHost:3306/metropos";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!@#0501083416!@#";


    // Private constructor to prevent instantiation
    private ConnectionConfig() { }

    // Method to get the connection
    public static Connection getConnection() {
        // Check if the connection is null or closed
        if (connection == null) {
            try {
                // Initialize the connection
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection established. teuly");
            } catch (SQLException e) {
                // Handle any errors
                System.err.println("Error establishing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}

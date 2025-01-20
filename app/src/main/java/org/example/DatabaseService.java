package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseService {
    private static final String JDBC_URL = String.format(
        "jdbc:postgresql://%s:%s/%s",
        System.getenv("POSTGRES_HOST"), // Use host from environment variable
        System.getenv("POSTGRES_PORT"), // Use port from environment variable
        System.getenv("POSTGRES_DB")    // Use database name from environment variable
    );
    private static final String DB_USER = System.getenv("POSTGRES_USER"); // User from environment variable
    private static final String DB_PASSWORD = System.getenv("POSTGRES_PASSWORD"); // Password from environment variable

    public void queryDatabase() {
        int retries = 5;
        while (retries > 0) {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
                System.out.println("JDBC URL: " + JDBC_URL);
    
                while (resultSet.next()) {
                    System.out.println("User: " + resultSet.getString("name") +
                                       ", Email: " + resultSet.getString("email"));
                }
                return; // Exit the loop if successful
            } catch (Exception e) {
                System.err.println("Database connection failed. Retrying...");
                e.printStackTrace();
                retries--;
                try {
                    Thread.sleep(2000); // Wait for 2 seconds before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.err.println("Could not connect to the database after multiple attempts.");
    }
}
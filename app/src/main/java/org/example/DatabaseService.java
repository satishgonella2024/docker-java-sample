package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseService {
    // Use the service name 'postgres' as the host
    private static final String JDBC_URL = "jdbc:postgresql://postgres:5432/sampledb";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";

    public void queryDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                System.out.println("User: " + resultSet.getString("name") +
                                   ", Email: " + resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
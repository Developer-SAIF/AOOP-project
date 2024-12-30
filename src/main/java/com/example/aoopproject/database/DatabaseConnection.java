package com.example.aoopproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://sql108.infinityfree.com:3306/if0_37983228_login_infos";
    private static final String USER = "if0_37983228";
    private static final String PASSWORD = "639infinity";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Connection successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }
}

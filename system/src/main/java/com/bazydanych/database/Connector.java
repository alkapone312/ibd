package main.java.com.bazydanych.database;

import java.sql.*;

public class Connector {
    private Connection connection;

    public Connector() {
        connection = null;
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://mysql/hotel?user=root&password=123"
            );
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

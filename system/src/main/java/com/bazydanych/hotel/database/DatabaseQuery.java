package main.java.com.bazydanych.hotel.database;

import java.sql.*;

public class DatabaseQuery {
    Connection connection;

    public DatabaseQuery(Connector connector) {
        connection = connector.getConnection();
    }

    public ResultSet select(String selectStatement) {
        try {
            Statement s = connection.createStatement();
            s.execute(selectStatement);
            return s.getResultSet();
        } catch (SQLException e) {
            System.out.println("Unable to fetch from database!");
            return null;
        }
    }

    public boolean query(String query) {
        try {
            Statement s = connection.createStatement();
            s.execute(query);
            return true;
        } catch (SQLException e) {
            System.out.println(e + "\n\n Failure for query " + query);
            return false;
        }
    }

    public CallableStatement prepare(String query) {
        try {
            return connection.prepareCall(query);
        } catch (SQLException e) {
            System.out.println(e + "\n\n Failure for query " + query);
            return null;
        }
    }
}

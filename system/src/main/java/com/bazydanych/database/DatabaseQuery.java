package main.java.com.bazydanych.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void query(String query) {
        try {
            Statement s = connection.createStatement();
            s.execute(query);
        } catch (SQLException e) {
            System.out.println(e + "\n\n Failure for query " + query);
        }
    }
}

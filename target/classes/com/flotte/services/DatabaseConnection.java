package com.flotte.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String jdbcUrl = "jdbc:mysql://172.17.0.2:3306/flotte_vehicule";
    private static final String user = "root";
    private static final String mdp = "";

    public static Connection GetConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, user, mdp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Gérer la ClassNotFoundException
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer la SQLException
        }
        return connection;
    }
}

package com.flotte.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String jdbcUrl = "jdbc:mysql://viaduct.proxy.rlwy.net:56049/railway";
    private static final String user = "root";
    private static final String mdp = "c3g4GCebACDcGbeAccEE6A46fHCEeGFc";

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

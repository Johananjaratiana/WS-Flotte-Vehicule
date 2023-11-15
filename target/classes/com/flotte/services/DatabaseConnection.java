package com.flotte.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String jdbcUrl;
    private static String user;
    private static String password;
    private static String driver;

    public static void setParameter() throws Exception
    {
        try{
            Properties prop = new Properties();
            FileInputStream in = new FileInputStream("my-config");
            prop.load(in);
            in.close();
            jdbcUrl = prop.getProperty("jdbc.url");
            user = prop.getProperty("jdbc.user");
            password = prop.getProperty("jdbc.password");
            driver = prop.getProperty("jdbc.driver");
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public static Connection GetConnection() throws Exception {
        Connection connection = null;
        try {
            setParameter();
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Gérer la ClassNotFoundException
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer la SQLException
        }
        return connection;
    }
}

package com.flotte.security.token;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flotte.services.DatabaseConnection;
import java.sql.Connection;
import java.util.Date;

public class Token {

    public static void saveToken(int userId, String token, Date expirationDate) throws Exception {
        try (Connection connection = DatabaseConnection.GetConnection()) {
            String query = "INSERT INTO flotte_vehicule.token (idusers, token, dtexp) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, token);
            preparedStatement.setDate(3, new java.sql.Date(expirationDate.getTime()));
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public static boolean isTokenValid(String token) throws Exception {
        try (Connection connection = DatabaseConnection.GetConnection()) {
            String query = "SELECT COUNT(*) FROM flotte_vehicule.token WHERE token = ? AND dtexp >= CURDATE() AND isvalidate = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public static void invalidateToken(int userId) throws Exception{
        try(Connection connection = DatabaseConnection.GetConnection()){
            String query = "UPDATE flotte_vehicule.token SET isvalidate = 0 WHERE idusers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}

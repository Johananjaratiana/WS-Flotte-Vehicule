package com.flotte.model.vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.flotte.services.DatabaseConnection;

public class Vehicule {
    private Integer id;
    private String mtr;
    private String nom;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMtr() {
        return mtr;
    }

    public void setMtr(String mtr) {
        this.mtr = mtr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static Vehicule save(Vehicule newVehicule) {
        Vehicule result = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "INSERT INTO vehicule (mtr, nom) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);

                // Set the values for the placeholders
                preparedStatement.setString(1, newVehicule.getMtr());
                preparedStatement.setString(2, newVehicule.getNom());

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            newVehicule.setId(generatedKeys.getInt(1));
                            result = newVehicule;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Vehicule> getVehicules() {
        List<Vehicule> vehicules = new ArrayList<>();
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM vehicule";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the results
                while (resultSet.next()) {
                    Vehicule vehicule = new Vehicule();
                    vehicule.setId(resultSet.getInt("id"));
                    vehicule.setMtr(resultSet.getString("mtr"));
                    vehicule.setNom(resultSet.getString("nom"));
                    vehicules.add(vehicule);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicules;
    }

    public static Vehicule getVehiculeById(Integer vehiculeId) {
        Vehicule vehicule = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM vehicule WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value for the placeholder
                preparedStatement.setInt(1, vehiculeId);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result
                if (resultSet.next()) {
                    vehicule = new Vehicule();
                    vehicule.setId(resultSet.getInt("id"));
                    vehicule.setMtr(resultSet.getString("mtr"));
                    vehicule.setNom(resultSet.getString("nom"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicule;
    }

    public static List<Vehicule> getAll(){
        List<Vehicule> vehicules = new ArrayList<>();
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM vehicule";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the results
                while (resultSet.next()) {
                    Vehicule vehicule = new Vehicule();
                    vehicule.setId(resultSet.getInt("id"));
                    vehicule.setMtr(resultSet.getString("mtr"));
                    vehicule.setNom(resultSet.getString("nom"));
                    vehicules.add(vehicule);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicules;
    }

    public static Vehicule update(Vehicule updatedVehicule) {
        Vehicule result = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "UPDATE vehicule SET mtr = ?, nom = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the values for the placeholders
                preparedStatement.setString(1, updatedVehicule.getMtr());
                preparedStatement.setString(2, updatedVehicule.getNom());
                preparedStatement.setInt(3, updatedVehicule.getId());

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    result = updatedVehicule;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean delete(Integer vehiculeId) {
        boolean success = false;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "DELETE FROM vehicule WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value for the placeholder
                preparedStatement.setInt(1, vehiculeId);

                // Execute the delete
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the delete was successful
                success = rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}

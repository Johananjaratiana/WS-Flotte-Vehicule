package com.flotte.model.kilometrage;

import com.flotte.services.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Kilometrage {

    private Integer id;
    private Integer idVehicule;
    private Date date_;
    private Double debutKm;
    private Double finKm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Integer idVehicule) {
        this.idVehicule = idVehicule;
    }

    // Getter and Setter for 'date_'
    public Date getDate_() {
        return date_;
    }

    public void setDate_(Date date_) {
        this.date_ = date_;
    }

    // Getter and Setter for 'debutKm'
    public Double getDebutKm() {
        return debutKm;
    }

    public void setDebutKm(Double debutKm) {
        this.debutKm = debutKm;
    }

    // Getter and Setter for 'finKm'
    public Double getFinKm() {
        return finKm;
    }

    public void setFinKm(Double finKm) {
        this.finKm = finKm;
    }

    // Constructors, getters, and setters

    public static Kilometrage save(Kilometrage kilometrage) {
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "INSERT INTO kilometrage (idvehicule, date_, debut_km) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS);

                // Set the values for the placeholders
                preparedStatement.setInt(1, kilometrage.getIdVehicule());
                preparedStatement.setDate(2, kilometrage.getDate_());
                preparedStatement.setDouble(3, kilometrage.getDebutKm());

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            kilometrage.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kilometrage;
    }

    public static List<Kilometrage> getKilometragesByVehiculeId(Integer vehiculeId) {
        List<Kilometrage> kilometrages = new ArrayList<>();
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM kilometrage WHERE idvehicule = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value for the placeholder
                preparedStatement.setInt(1, vehiculeId);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the results
                while (resultSet.next()) {
                    Kilometrage kilometrage = new Kilometrage();
                    kilometrage.setId(resultSet.getInt("id"));
                    kilometrage.setIdVehicule(resultSet.getInt("idvehicule"));
                    kilometrage.setDate_(resultSet.getDate("date_"));
                    kilometrage.setDebutKm(resultSet.getDouble("debut_km"));
                    kilometrage.setFinKm(resultSet.getDouble("fin_km"));
                    kilometrages.add(kilometrage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kilometrages;
    }


    public static List<Kilometrage> getAll() {
        List<Kilometrage> kilometrages = new ArrayList<>();
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM kilometrage";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the results
                while (resultSet.next()) {
                    Kilometrage kilometrage = new Kilometrage();
                    kilometrage.setId(resultSet.getInt("id"));
                    kilometrage.setIdVehicule(resultSet.getInt("idvehicule"));
                    kilometrage.setDate_(resultSet.getDate("date_"));
                    kilometrage.setDebutKm(resultSet.getDouble("debut_km"));
                    kilometrage.setFinKm(resultSet.getDouble("fin_km"));
                    kilometrages.add(kilometrage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kilometrages;
    }

    public static Kilometrage getKilometrageById(Integer id) {
        Kilometrage kilometrage = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM kilometrage WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value for the placeholder
                preparedStatement.setInt(1, id);

                // Execute the query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Process the result
                if (resultSet.next()) {
                    kilometrage = new Kilometrage();
                    kilometrage.setId(resultSet.getInt("id"));
                    kilometrage.setIdVehicule(resultSet.getInt("idvehicule"));
                    kilometrage.setDate_(resultSet.getDate("date_"));
                    kilometrage.setDebutKm(resultSet.getDouble("debut_km"));
                    kilometrage.setFinKm(resultSet.getDouble("fin_km"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kilometrage;
    }

    public static Kilometrage update(Kilometrage updatedKilometrage) {
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "UPDATE kilometrage SET fin_km = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the values for the placeholders
                preparedStatement.setDouble(1, updatedKilometrage.getFinKm());
                preparedStatement.setInt(2, updatedKilometrage.getId());

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    return updatedKilometrage;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean delete(Integer id) {
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "DELETE FROM kilometrage WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Set the value for the placeholder
                preparedStatement.setInt(1, id);

                // Execute the delete
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the delete was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Getter and setter methods...
}

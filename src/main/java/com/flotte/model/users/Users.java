package com.flotte.model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flotte.services.DatabaseConnection;

public class Users {
    Integer id;
    Integer idProfile;
    String nom;
    String mdp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static Users VerifyExistingUsers(String nom, String mdp) {
        Users users = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT * FROM users WHERE nom = ? AND mdp = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, mdp);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    users = new Users();
                    users.setId(resultSet.getInt("id"));
                    users.setIdProfile(resultSet.getInt("idprofile"));
                    users.setNom(resultSet.getString("nom"));                    
                    users.setMdp(resultSet.getString("mdp"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}

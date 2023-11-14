package com.flotte.model.profil_utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flotte.services.DatabaseConnection;

public class ProfilUtilisateur {
    private int id;
    private String nom;
    
    public ProfilUtilisateur(){}

    public ProfilUtilisateur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public static ProfilUtilisateur GetProfilUtilisateurByUserId(Integer userId) throws Exception{
        ProfilUtilisateur profilUtilisateur = null;
        try {
            try (Connection connection = DatabaseConnection.GetConnection()) {
                String sql = "SELECT pu.id, pu.nom FROM profil_utilisateur pu"+
                            " JOIN users u ON u.idprofile = pu.id WHERE u.idprofile = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    profilUtilisateur = new ProfilUtilisateur();
                    profilUtilisateur.setId(resultSet.getInt("id"));
                    profilUtilisateur.setNom(resultSet.getString("nom"));
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return profilUtilisateur;
    }

}

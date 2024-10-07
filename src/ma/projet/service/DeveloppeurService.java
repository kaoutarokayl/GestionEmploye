/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

/**
 *
 * @author Pc
 */
public class DeveloppeurService implements IDao <Developpeur>{

    @Override
 public boolean create(Developpeur D) {
    try {
        // Insérer la personne dans la table Personne
        String reqPersonne = "INSERT INTO Personne (nom, salaire) VALUES (?, ?)";
        PreparedStatement psPersonne = Connexion.getConnection().prepareStatement(reqPersonne, Statement.RETURN_GENERATED_KEYS);
        psPersonne.setString(1, D.getNom());
        psPersonne.setDouble(2, D.getSalaire());

        // Exécuter l'insertion dans la table Personne
        if (psPersonne.executeUpdate() == 1) {
            // Récupérer l'ID généré automatiquement pour Personne
            ResultSet rs = psPersonne.getGeneratedKeys();
            if (rs.next()) {
                int idPersonne = rs.getInt(1);  // Récupérer l'ID généré

                // Insérer ensuite dans la table Developpeur avec le même ID
                String reqDeveloppeur = "INSERT INTO Developpeur (id) VALUES (?)";
                PreparedStatement psDeveloppeur = Connexion.getConnection().prepareStatement(reqDeveloppeur);
                psDeveloppeur.setInt(1, idPersonne);

                if (psDeveloppeur.executeUpdate() == 1) {
                    return true;
                }
            }
        }
    } catch (SQLException ex) {
        System.out.println("Erreur de la connexion : " + ex.getMessage());
    }
    return false;
}


    @Override
    public boolean update(Developpeur D) {
        try {
    String req = "update developpeur set nom = ? , salaire = ? where id =?";
    PreparedStatement ps =
    Connexion.getConnection().prepareStatement(req);
    ps.setString(1, D.getNom());
    ps.setDouble(2, D.getSalaire());
    ps.setInt(3, D.getId());
    if (ps.executeUpdate() == 1) {
    return true;
    }
    } catch (SQLException ex) {
    Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
    }
    return false;
    }
    @Override
    public boolean delete(Developpeur D) {
         try {
    String req = "delete from developpeur where id = ?";
    PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
    ps.setInt(1, D.getId());
    if (ps.executeUpdate() == 1) {
    return true;
    }
    } catch (SQLException ex) {
    Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null, ex);
    }
    return false;
    }
    @Override
    public Developpeur getById(int id) {
     Developpeur dev = null;
   try {
    String req = "select * from developpeur where id = ?";
    PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if(rs.next())
    dev = new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
    } catch (SQLException ex) {
    System.out.println("Impossible de trouver ce developpeur "+ dev.getNom());
    }
    return dev;
    }
    @Override
public List<Developpeur> getAll() {
    List<Developpeur> dev = new ArrayList<>();
    try {
        // Requête pour joindre les tables Developpeur et Personne
        String req = "SELECT p.id, p.nom, p.salaire FROM Developpeur d JOIN Personne p ON d.id = p.id";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        
        // Parcourir les résultats et ajouter à la liste
        while (rs.next()) {
            dev.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
        } 
   
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return dev;
}
public List<Developpeur> getDeveloppeursUnderManager(Manager manager) {
    List<Developpeur> developpeurs = new ArrayList<>();
    try {
        String req = "SELECT p.id, p.nom, p.salaire FROM Gestion g JOIN Personne p ON g.id_employe = p.id WHERE g.id_manager = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, manager.getId());
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            developpeurs.add(new Developpeur(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return developpeurs;
}

}
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
import ma.projet.beans.Manager;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

/**
 *
 * @author Pc
 */
public class ManagerService implements IDao <Manager> {
      @Override
public boolean create(Manager M) {
    try {
        // Insérer la personne dans la table Personne
        String reqPersonne = "INSERT INTO Personne (nom, salaire) VALUES (?, ?)";
        PreparedStatement psPersonne = Connexion.getConnection().prepareStatement(reqPersonne, Statement.RETURN_GENERATED_KEYS);
        psPersonne.setString(1, M.getNom());
        psPersonne.setDouble(2, M.getSalaire());

        // Exécuter l'insertion dans la table Personne
        if (psPersonne.executeUpdate() == 1) {
            // Récupérer l'ID généré automatiquement pour Personne
            ResultSet rs = psPersonne.getGeneratedKeys();
            if (rs.next()) {
                int idPersonne = rs.getInt(1);  // Récupérer l'ID généré

                // Insérer ensuite dans la table Manager avec le même ID
                String reqManager = "INSERT INTO Manager (id) VALUES (?)";
                PreparedStatement psManager = Connexion.getConnection().prepareStatement(reqManager);
                psManager.setInt(1, idPersonne);

                if (psManager.executeUpdate() == 1) {
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
    public boolean update(Manager M) {
        try {
    String req = "update manager set nom = ? , salaire = ? where id =?";
    PreparedStatement ps =
    Connexion.getConnection().prepareStatement(req);
    ps.setString(1, M.getNom());
    ps.setDouble(2, M.getSalaire());
    ps.setInt(3, M.getId());
    if (ps.executeUpdate() == 1) {
    return true;
    }
    } catch (SQLException ex) {
    System.out.println("Erreur de faire la màj");
    }
    return false;
    }
    @Override
    public boolean delete(Manager M) {
         try {
    String req = "delete from manager where id = ?";
    PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
    ps.setInt(1, M.getId());
    if (ps.executeUpdate() == 1) {
    return true;
    }
    } catch (SQLException ex) {
   System.out.println("Impossible de supprimer le manager");
    }
    return false;
    }
    
    @Override
    public Manager getById(int id) {
   Manager ma = null;
   try {
    String req = "select * from manager where id = ?";
    PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    if(rs.next())
    ma = new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire"));
    } catch (SQLException ex) {
    System.out.println("Impossible de trouver ce manager "+ ma.getNom());
    }
  

    return ma;
    }
    @Override

public List<Manager> getAll() {
    List<Manager> ma = new ArrayList<>();
    try {
        // Requête pour joindre les tables Manager et Personne
        String req = "SELECT p.id, p.nom, p.salaire FROM Manager m JOIN Personne p ON m.id = p.id";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        
        // Parcourir les résultats et ajouter à la liste
        while (rs.next()) {
            ma.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
        } 
   
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ma;
}

public List<Manager> getManagersUnderDirecteur(Manager directeur) {
    List<Manager> managers = new ArrayList<>();
    try {
        String req = "SELECT p.id, p.nom, p.salaire FROM Gestion g JOIN Personne p ON g.id_employe = p.id WHERE g.id_manager = ?";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setInt(1, directeur.getId());
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            managers.add(new Manager(rs.getInt("id"), rs.getString("nom"), rs.getDouble("salaire")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return managers;
}

}
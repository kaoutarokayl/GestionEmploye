/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

/**
 *
 * @author Pc
 */
public class Manager extends Personne {

    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }

    public Manager(String nom, double salaire) {
        super(nom, salaire);
    }

    
}
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
public class Developpeur extends Personne {

    public Developpeur(int id, String nom , double salaire) {
        super(id, nom, salaire);
    }

    public Developpeur(String nom, double salaire) {
        super(nom, salaire);
    }


    
    
}
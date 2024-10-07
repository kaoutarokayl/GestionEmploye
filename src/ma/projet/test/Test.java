package ma.projet.test;

import java.util.List;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Manager;
import ma.projet.service.DeveloppeurService;
import ma.projet.service.ManagerService;

public class Test {
    public static void main(String [] args){
        DeveloppeurService devService = new DeveloppeurService();
        ManagerService manService = new ManagerService();
        
        // Création du directeur général, manager et développeurs
        Manager directeurGeneral = new Manager("Directeur General", 10000);
        manService.create(directeurGeneral);
        
        Manager manager1 = new Manager("Manager1", 4000);
        manService.create(manager1);
        
        devService.create(new Developpeur("Dev1", 3500));
        devService.create(new Developpeur("Dev2", 2000));
        devService.create(new Developpeur("Dev3", 1500));
        
        // Affichage de la hiérarchie
        System.out.println("---- Hiérarchie des employés ----");

        // Afficher le directeur général
        for (Manager m : manService.getAll()) {
            if (m.getNom().equals("Directeur General")) {
                System.out.println("Le Directeur Général est : " + m.getNom() + " et son salaire est " + m.getSalaire());
                
                // Afficher les managers gérés par le directeur général
                for (Manager manager : manService.getManagersUnderDirecteur(m)) {
                    System.out.println("\tLe Manager est : " + manager.getNom() + " et son salaire est " + manager.getSalaire());
                    
                    // Afficher les développeurs gérés par ce manager
                    for (Developpeur dev : devService.getDeveloppeursUnderManager(manager)) {
                        System.out.println("\t\tLe Développeur est : " + dev.getNom() + " et son salaire est " + dev.getSalaire());
                    }
                }
            }
        }
    }
}

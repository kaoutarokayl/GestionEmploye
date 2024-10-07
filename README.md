# Gestion des Employés

## Description du Projet

Ce projet est une application de gestion des employés pour une petite entreprise. Il permet de gérer une hiérarchie d'employés, incluant un directeur général, un manager et plusieurs développeurs. L'application utilise JDBC pour interagir avec une base de données MySQL.

## Fonctionnalités

- Affichage des employés selon leur hiérarchie.
- Gestion des opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) pour les employés.
- Connexion à une base de données MySQL via un fichier de configuration.

## Structure du Projet

Le projet est organisé comme suit :

/mon-projet │ ├── /src │ ├── /ma.projet.beans │ │ ├── Employe.java │ │ ├── Manager.java │ │ └── Developpeur.java │ │ │ ├── /ma.projet.connexion │ │ └── Connexion.java │ │ │ ├── /ma.projet.dao │ │ └── IDao.java │ │ │ ├── /ma.projet.service │ │ ├── ManagerService.java │ │ └── DeveloppeurService.java │ │ │ └── /ma.projet.test │ └── Test.java │ ├── base.properties └── README.md


## Prérequis

- Java JDK 8 ou supérieur
- MySQL Server
- JDBC Driver pour MySQL


  ## Vidéo Démonstrative


https://github.com/user-attachments/assets/5aa1ed94-0dae-4195-8ac2-cc18e40edc3c





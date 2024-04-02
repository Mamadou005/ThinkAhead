package modele;

import java.util.LinkedList;
import java.util.Scanner;

public class Partie {
    private Grille laGrille;
    private Joueur[] joueurs;
    private LinkedList<Coup> lesCoups;
    private Integer[] score;
    private int indiceJoueurCourant;

    // Méthode pour passer au joueur suivant
    public void passerAuJoueurSuivant() {
        // Augmenter l'index du joueur courant
        indiceJoueurCourant++;

        // Si l'index dépasse la taille de la liste des joueurs, revenir au premier joueur
        if (indiceJoueurCourant >= joueurs.length) {
            indiceJoueurCourant = 0;
        }
    }

    public Joueur getJoueurCourant() {
        return joueurs[indiceJoueurCourant];
    }


    public Grille getLaGrille() {
        return laGrille;
    }

    public void setLaGrille(Grille laGrille) {
        this.laGrille = laGrille;
    }

    public Joueur getJoueurs(int j) {
        return joueurs[j];
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public LinkedList<Coup> getLesCoups() {
        return lesCoups;
    }

    public void setLesCoups(LinkedList<Coup> lesCoups) {
        this.lesCoups = lesCoups;
    }

    public Integer[] getScore() {
        return score;
    }

    public void setScore(Integer[] score) {
        this.score = score;
    }
    public void commencer(){

        // Initialisation de la grille
        initialiserGrille();

        // Initialisation des joueurs
        initialiserJoueurs();

        // Initialisation des scores
        score = new Integer[]{0, 0};

        // Initialisation de la liste des coups
        lesCoups = new LinkedList<>();

        // Affichage de la grille initiale
        afficherGrille();
        
    }
    // Méthode pour ajouter un coup à la partie
    public boolean ajouterCoup(Coup coup) {
        if(laGrille.selectionnerCase(coup.getPositionCase(), coup.getJoueur())){
            lesCoups.add(coup) ; // Ajout du coup au tableau
            return true;
        };
        return false;
    }
    public void annulerCoup(Integer nbr){
        for (int i = 0; i < nbr && !lesCoups.isEmpty(); i++) {
            lesCoups.removeLast();
        }
    }

    // Méthode pour initialiser la grille avec une configuration spécifique
    private void initialiserGrille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une configuration pour la grille :");
        System.out.println("1. Aléatoire");
        System.out.println("2. Réparties en quartiles");
        System.out.println("3. Personnalisée");

        int choix = scanner.nextInt();
        switch (choix) {
            case 1:
                choisirTailleGrille();
                laGrille.initializeGridWithRandomValues();
                break;
            case 2:
                choisirTailleGrille();
                laGrille.initializeGridWithQuartileValues();
                break;
            case 3:
                // Logique pour une configuration personnalisée (non implémentée ici)
                break;
            default:
                System.out.println("Choix invalide. Utilisation de la configuration par défaut (aléatoire).");
                laGrille = new Grille();
                laGrille.initializeGridWithRandomValues();
                break;
        }
    }

    // Méthode pour initialiser les joueurs
    private void initialiserJoueurs() {
        Scanner scanner = new Scanner(System.in);
        joueurs = new Joueur[2];

        for (int i = 0; i < joueurs.length; i++) {
            System.out.println("Joueur " + (i + 1) + " :");
            System.out.println("1. Humain");
            System.out.println("2. Ordinateur");
            int choix = scanner.nextInt();
            if (choix == 1) {
                System.out.println("Entrez le nom du joueur : ");
                String nom = scanner.next();
                Joueur joueur = new JoueurHumain();
                joueur.setNom(nom);
                joueurs[i] = joueur;
            } else if (choix == 2) {
                Joueur joueur = new JoueurOrdinateur();
                joueur.setNom("Ordinateur " + (i + 1));
                joueurs[i] = joueur;
            } else {
                System.out.println("Choix invalide. Le joueur sera un humain par défaut.");
                System.out.println("Entrez le nom du joueur : ");
                String nom = scanner.next();
                Joueur joueur = new JoueurHumain();
                joueur.setNom(nom);
                joueurs[i] = joueur;
            }
        }
    }

    public void afficherGrille() {
        System.out.println("Grille de jeu :");
        for (int i = 0; i < laGrille.getTaille(); i++) {
            for (int j = 0; j < laGrille.getTaille(); j++) {
                Case caseCourante = laGrille.getCase(new Position(i, j));
                if (caseCourante != null) {
                    if (caseCourante.getJouePar() != null) {
                        System.out.print(caseCourante.getJouePar().getNom() + " "); // Afficher le nom du joueur ayant joué sur la case
                    } else {
                        System.out.print(caseCourante.getValeur() + " "); // Afficher la valeur de la case si elle n'a pas été jouée
                    }
                } else {
                    System.out.print("- "); // Afficher un tiret si la case est vide
                }
            }
            System.out.println(); // Aller à la ligne après chaque ligne de la grille
        }
    }

    public void choisirTailleGrille() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir la taille de la grille : ");
        int taille = scanner.nextInt();

        // Valider la taille saisie (par exemple, s'assurer qu'elle est positive)
        if (taille <= 0) {
            throw new IllegalArgumentException("La taille de la grille doit être un entier positif.");
        }
        laGrille = new Grille();

        laGrille.Case(taille);
    }

    public boolean estTerminee() {
        // Vérifier si toutes les cases de la grille ont été sélectionnées
        if (laGrille.getAlignementActif() == null) return false;
        Case[] cases = laGrille.getAlignementActif().getCasesLibre();
        for (Case caseGrille : cases) {
            if (caseGrille.estLibre()) {
                return false; // La partie n'est pas terminée si au moins une case est libre
            }
        }

        // Vérifier d'autres conditions de fin de partie, le cas échéant...

        return true; // La partie est terminée si aucune case n'est libre et aucune autre condition de fin de partie n'est remplie
    }

}

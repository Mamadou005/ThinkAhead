package modeText;

import modele.*;

import java.util.Scanner;

public class AfficherConsole {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.commencer();

        // Boucle principale du jeu
        while (!partie.estTerminee()) {
            // Afficher l'état actuel de la grille
            partie.afficherGrille();

            // Demander au joueur en cours de jouer un coup
            jouerCoup(partie);

            // Passer au joueur suivant
            partie.passerAuJoueurSuivant();
        }
        // Afficher le résultat final de la partie
        afficherResultatFinal(partie);
    }

    private static void jouerCoup(Partie partie) {
        Grille grille = partie.getLaGrille();
        Joueur joueurCourant = partie.getJoueurCourant();

        System.out.println("C'est au tour de " + joueurCourant.getNom() + " de jouer.");

        // Demander au joueur de saisir les coordonnées de la case à jouer
        System.out.print("Veuillez saisir la ligne de la case (1-" + grille.getTaille() + ") : ");
        int ligne = scanner.nextInt();
        System.out.print("Veuillez saisir la colonne de la case (1-" + grille.getTaille() + ") : ");
        int colonne = scanner.nextInt();

        // Demander au joueur de saisir l'orientation du coup
        System.out.println("Veuillez saisir l'orientation du coup (H pour horizontale, V pour verticale) : ");
        char orientationChar = scanner.next().charAt(0);
        Orientation orientation = new Orientation( (orientationChar == 'H' || orientationChar == 'h') ? 1 : 0 );

        // Créer un nouvel objet Coup avec les données saisies
        Position position = new Position(ligne - 1, colonne - 1);
        Coup coup = new Coup(joueurCourant, position, orientation);

        if (grille.getAlignementActif() == null){
            grille.setAlignementActif(ligne, orientation);
        }

        // Jouer le coup dans la partie
        if (partie.ajouterCoup(coup)) {
            System.out.println("Coup joué avec succès !");
        } else {
            System.out.println("Case déjà occupée ou coordonnées invalides. Veuillez réessayer.");
            jouerCoup(partie); // Demander au joueur de rejouer un coup valide
        }
    }

    private static void afficherResultatFinal(Partie partie) {
        System.out.println("La partie est terminée !");
        System.out.println("Résultat final :");
        int numJoueur = 0;
        for (int score : partie.getScore()){
            Joueur joueur = partie.getJoueurs(numJoueur);
            System.out.println(joueur.getNom() + " : " + score + " points");
            numJoueur++;
        }
    }


}

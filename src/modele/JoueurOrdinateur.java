package modele;

import java.util.ArrayList;
import java.util.Collections;

public class JoueurOrdinateur extends Joueur{
    private String typeDejeu;
    private Integer niveau;

    private Joueur adversaire;

    public void setAdversaire(Joueur adversaire) {
        this.adversaire = adversaire;
    }
    @Override
    public Position aJoueEn() {
        return null;
    }

    // Méthode pour que l'ordinateur choisisse une case au hasard parmi celles qui sont libres
    public Case choisirCaseAuHasard(Grille grille) {
        ArrayList<Case> casesLibres = new ArrayList<>();
        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Case caseCourante = grille.getCase(new Position(i, j));
                if (caseCourante.estLibre()) {
                    casesLibres.add(caseCourante);
                }
            }
        }
        Collections.shuffle(casesLibres); // Mélanger les cases
        return casesLibres.get(0); // Choisir une case au hasard parmi celles qui sont libres
    }

    // Méthode pour que l'ordinateur choisisse la case libre ayant la valeur maximale
    public Case choisirCaseValeurMax(Grille grille) {
        // Trouver la case avec la valeur maximale
        return grille.getAlignementActif().getCaseLibreValeurMax();
    }

    // Méthode pour que l'ordinateur choisisse la case permettant d’avoir un écart maximal entre la valeur de cette case et celle que choisira son adversaire au prochain coup
    public Case choisirCaseEcartMax(Grille grille, Joueur joueurAdverse) {
        ArrayList<Case> casesLibres = new ArrayList<>();
        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Case caseCourante = grille.getCase(new Position(i, j));
                if (caseCourante.estLibre()) {
                    casesLibres.add(caseCourante);
                }
            }
        }
        if (casesLibres.isEmpty()) {
            return null; // Aucune case libre
        }
        // Calculer l'écart maximal entre la valeur de cette case et celle que choisira son adversaire au prochain coup
        Case caseEcartMax = casesLibres.get(0);
        int ecartMax = Integer.MIN_VALUE;
        for (Case caseLibre : casesLibres) {
            // Simuler le coup de l'adversaire sur cette case
            caseLibre.setJouePar(joueurAdverse);
            int valeurAdversaire = choisirCaseValeurMax(grille).getValeur();
            int ecart = caseLibre.getValeur() - valeurAdversaire;
            if (ecart > ecartMax) {
                ecartMax = ecart;
                caseEcartMax = caseLibre;
            }
            // Annuler le coup simulé de l'adversaire
            caseLibre.setJouePar(null);
        }
        return caseEcartMax;
    }

    // Méthode pour choisir la case libre ayant la valeur maximale avec un niveau de profondeur paramétrable
    public Case choisirCaseProfondeurMax(Grille grille, int profondeur) {
        // Obtenir une liste de toutes les cases libres
        ArrayList<Case> casesLibres = obtenirCasesLibres(grille);

        if (casesLibres.isEmpty()) {
            return null; // Aucune case libre
        }

        // Initialisation de la meilleure case et du meilleur score
        Case meilleureCase = null;
        int meilleurScore = Integer.MIN_VALUE;

        // Itérer sur toutes les cases libres et évaluer le score de chacune
        for (Case caseLibre : casesLibres) {
            // Simuler le coup de l'ordinateur sur cette case
            caseLibre.setJouePar(this);
            // Calculer le score de cette configuration en appelant une fonction récursive pour évaluer la position
            int score = evaluerPosition(grille, profondeur - 1, false);
            // Annuler le coup simulé
            caseLibre.setJouePar(null);
            // Mettre à jour la meilleure case si le score est meilleur
            if (score > meilleurScore) {
                meilleurScore = score;
                meilleureCase = caseLibre;
            }
        }

        return meilleureCase;
    }

    // Méthode récursive pour évaluer une position jusqu'à un certain niveau de profondeur
    private int evaluerPosition(Grille grille, int profondeur, boolean tourJoueurMax) {
        // Cas de base : la profondeur maximale est atteinte ou la partie est terminée
        if (profondeur == 0 || estFinPartie(grille)) {
            // Évaluer la position actuelle et retourner le score
            return evaluerPositionActuelle(grille);
        }

        // Obtenir une liste de toutes les cases libres
        ArrayList<Case> casesLibres = obtenirCasesLibres(grille);

        if (casesLibres.isEmpty()) {
            return 0; // Aucune case libre
        }

        // Initialisation du score minimal ou maximal en fonction du joueur
        int scoreMin = Integer.MAX_VALUE;
        int scoreMax = Integer.MIN_VALUE;

        // Itérer sur toutes les cases libres
        for (Case caseLibre : casesLibres) {
            // Simuler le coup du joueur actuel sur cette case
            if (tourJoueurMax) {
                caseLibre.setJouePar(this);
            } else {
                caseLibre.setJouePar(adversaire);
            }

            // Évaluer la position récursivement avec une profondeur réduite
            int score = evaluerPosition(grille, profondeur - 1, !tourJoueurMax);

            // Annuler le coup simulé
            caseLibre.setJouePar(null);

            // Mettre à jour le score minimal ou maximal en fonction du joueur
            if (tourJoueurMax && score > scoreMax) {
                scoreMax = score;
            }
            if (!tourJoueurMax && score < scoreMin) {
                scoreMin = score;
            }
        }

        // Retourner le score minimal ou maximal en fonction du joueur
        return tourJoueurMax ? scoreMax : scoreMin;
    }

    private int evaluerPositionActuelle(Grille grille) {
        int score = 0;

        // Exemple de critères d'évaluation :
        // - Somme des valeurs des cases occupées par l'ordinateur
        // - Moins la somme des valeurs des cases occupées par l'adversaire
        // - Pénalités pour certaines configurations défavorables
        // - Récompenses pour certaines configurations favorables

        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Case caseCourante = grille.getCase(new Position(i, j));
                if (!caseCourante.estLibre()) {
                    if (caseCourante.getJouePar() == this) {
                        score += caseCourante.getValeur();
                    } else {
                        score -= caseCourante.getValeur();
                    }
                }
            }
        }

        // Exemple de pénalités ou de récompenses supplémentaires en fonction de certaines configurations

        // Retourner le score évalué pour la position actuelle
        return score;
    }


    private ArrayList<Case> obtenirCasesLibres(Grille grille) {
        ArrayList<Case> casesLibres = new ArrayList<>();
        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                Case caseCourante = grille.getCase(new Position(i, j));
                if (caseCourante.estLibre()) {
                    casesLibres.add(caseCourante);
                }
            }
        }
        return casesLibres;
    }

    private boolean estFinPartie(Grille grille) {
        // Vérifier si la grille est entièrement remplie
        for (int i = 0; i < grille.getTaille(); i++) {
            for (int j = 0; j < grille.getTaille(); j++) {
                if (grille.getCase(new Position(i, j)).estLibre()) {
                    return false; // S'il y a encore une case libre, la partie n'est pas terminée
                }
            }
        }
        return true; // Si toutes les cases sont occupées, la partie est terminée
    }


}

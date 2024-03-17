package modele;

import java.util.LinkedList;

public class Partie {
    private Grille laGrille;
    private Joueur[] joueurs;
    private LinkedList<Coup> lesCoups;
    private Integer[] score;

    public Grille getLaGrille() {
        return laGrille;
    }

    public void setLaGrille(Grille laGrille) {
        this.laGrille = laGrille;
    }

    public Joueur[] getJoueurs(int j) {
        return joueurs;
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
    }
    public void ajouterCoup(Coup coup){

    }
    public void annulerCoup(Integer nbr){

    }

}

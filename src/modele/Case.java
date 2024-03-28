package modele;

public class Case {
    private Integer valeur;
    private Joueur jouePar;
    private Position position;
    public boolean estLibre(){

        return false;
    }
    
    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
    }

    public Joueur getJouePar() {
        return jouePar;
    }

    public void setJouePar(Joueur jouePar) {
        this.jouePar = jouePar;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}

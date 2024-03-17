package modele;

public abstract class Joueur {
    private String nom;

    public abstract Position aJoueEn();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

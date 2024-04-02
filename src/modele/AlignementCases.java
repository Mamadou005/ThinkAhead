package modele;

import java.util.LinkedList;

public class AlignementCases {
    private Orientation orientation;
    private Case[] lesCases;
    private Integer position;

    public Orientation getOrientation() {
        return orientation;
    }

    public Case[] getCasesLibre() {
        return lesCases;
    }

    // Méthode pour obtenir la case libre avec la valeur maximale dans l'alignement
    public Case getCaseLibreValeurMax() {
        Case caseValeurMax = null;
        int valeurMax = Integer.MIN_VALUE;

        for (Case uneCase : lesCases) {
            if (uneCase.estLibre() && uneCase.getValeur() > valeurMax) {
                caseValeurMax = uneCase;
                valeurMax = uneCase.getValeur();
            }
        }

        return caseValeurMax;
    }
    public Case getCaseNUm(int num){
        return lesCases[num - 1];
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Case[] getLesCases() {
        return lesCases;
    }

    public void setLesCases(Case[] lesCases) {
        this.lesCases = lesCases;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

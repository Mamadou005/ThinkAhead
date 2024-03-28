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

    public Case getCaseLibreValeurMax() {
        return null;
    }
    public Case getCaseNUm(int num){
        return lesCases[num];
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

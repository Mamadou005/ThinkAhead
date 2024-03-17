package modele;

import java.util.LinkedList;

public class AlignementCases {
    private Orientation orientation;
    private LinkedList<Case> lesCases;
    private Integer position;

    public Orientation getOrientation() {
        return orientation;
    }

    public LinkedList<Case> getCasesLibre() {
        return lesCases;
    }

    public Case getCaseLibreValeurMax() {
        return null;
    }
    public Case getCaseNUm(int num){
        return lesCases.get(num);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public LinkedList<Case> getLesCases() {
        return lesCases;
    }

    public void setLesCases(LinkedList<Case> lesCases) {
        this.lesCases = lesCases;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

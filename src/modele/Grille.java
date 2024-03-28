package modele;

import java.util.LinkedList;

public class Grille {
    private AlignementCases alignementActif;
    private Case[] lesCases;
    public void Case(Integer n){

    }
    /**
     * Cette methode permet de recuperer une case en connaissant sa position
     * @param pos est la position de la case rechercher
     * @return case
     */
    public Case getCase(Position pos){
        Case casee = null;
        for(Case cas : lesCases){
            Position position = cas.getPosition();
            if(position.getPos_x().equals(pos.getPos_x()) && position.getPos_y().equals(pos.getPos_y())){
                casee = cas;
                break;
            }
        }
        return casee;
    }
    public boolean selectionCase(Position pos,Joueur joueur){
        return false;
    }
    public void setAlignementActif(Integer num,Orientation orientation){

    }
}

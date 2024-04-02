package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grille {
    private AlignementCases alignementActif;
    private Case[][] lesCases;

    private Integer taille;
    public void Case(Integer n){
        lesCases = new Case[n][n];
        taille = n;
        // Initialiser les cases de la grille
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lesCases[i][j] = new Case();
                Position position = new Position(i, j);
                lesCases[i][j].setPosition(position);
            }
        }
    }

    /**
     * Cette methode permet de recuperer une case en connaissant sa position
     * @param pos est la position de la case rechercher
     * @return case
     */
    public Case getCase(Position pos){

        if (pos.getPos_x() >= 0 && pos.getPos_x() < lesCases.length && pos.getPos_y() >= 0 && pos.getPos_y() < lesCases[0].length) {
            return lesCases[pos.getPos_x()][pos.getPos_y()];
        } else {
            // Gérer le cas où les coordonnées sont invalides (hors limites de la grille)
            return null;
        }
    }
    // Méthode pour sélectionner une case dans la grille
    public boolean selectionnerCase(Position pos, Joueur joueur) {
        if (pos.getPos_x() >= 0 && pos.getPos_x() < lesCases.length && pos.getPos_y() >= 0 && pos.getPos_y() < lesCases[0].length) {
            Case selectedCase = lesCases[pos.getPos_x()][pos.getPos_y()];
            // Vérifier si la case est libre
            if (selectedCase.estLibre()) {
                boolean isJouable = false;
                for (Case cas : alignementActif.getCasesLibre()){
                   if(cas.getPosition().getPos_x().equals(selectedCase.getPosition().getPos_x())
                           && cas.getPosition().getPos_y().equals(selectedCase.getPosition().getPos_y())){
                       isJouable = true;
                       break;
                   }
                }
                selectedCase.setJouePar(joueur);
                return isJouable || alignementActif.getCasesLibre() == null; // La case a été sélectionnée avec succès
            } else {
                return false; // La case est déjà occupée
            }
        } else {
            return false; // Les coordonnées sont invalides
        }
    }

    // Méthode pour définir l'alignement actif
    public void setAlignementActif(int num, Orientation orientation) {
        List<Case> casesAlignement = new ArrayList<>();
        num = num - 1;

        // Ajouter les cases de l'alignement en fonction de l'orientation
        if (orientation.estHorizontale()){
            for (int i = 0; i < lesCases.length; i++) {
                casesAlignement.add(lesCases[i][num]);
            }
        }

        if (orientation.estVertical()){
            for (int i = 0; i < lesCases[0].length; i++) {
                casesAlignement.add(lesCases[num][i]);
            }
        }

        // Créer un nouvel objet AlignementCases avec les cases appropriées
        alignementActif = new AlignementCases();
        alignementActif.setLesCases(casesAlignement.toArray(new Case[0]));
        alignementActif.setPosition(num);
        alignementActif.setOrientation(orientation);
    }

    // Méthode pour initialiser la grille avec une case à une valeur aléatoire entre 0 et 9 (Configuration 1)
    public void initializeGridWithRandomValues() {
        // Méthode pour initialiser la grille avec des valeurs aléatoires
            Random random = new Random();
            for (int i = 0; i < lesCases.length; i++) {
                for (int j = 0; j < lesCases[0].length; j++) {
                    lesCases[i][j].setValeur(random.nextInt(10)); // Génère une valeur aléatoire entre 0 et 9
                }
            }
    }

    // Méthode pour initialiser la grille avec un quart des cases à la valeur 1, un quart à la valeur 2, et ainsi de suite (Configuration 2)
    public void initializeGridWithQuartileValues() {
        Random random = new Random();
        int[] quartiles = {16, 12, 9, 7, 5, 4, 3, 2, 2}; // Nombre de valeurs par quartile
        int value = 1; // Valeur initiale du quartile
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[0].length; j++) {
                // Vérifier si le quartile est épuisé, passer au suivant
                while (quartiles[value - 1] == 0) {
                    value++;
                }
                lesCases[i][j].setValeur(value); // Attribuer la valeur du quartile actuel à la case
                quartiles[value - 1]--; // Décrémenter le nombre de valeurs restantes dans ce quartile
            }
        }
    }

    // Méthode pour initialiser la grille en utilisant un tableau de valeurs spécifiées avec leur nombre d'occurrences (Configuration 3)
    public void initializeGridWithCustomValues(int[][] customValues) {
        int index = 0;
        for (int[] customValue : customValues) {
            int value = customValue[0];
            int count = customValue[1];
            for (int i = 0; i < count; i++) {
                int x = index / lesCases.length;
                int y = index % lesCases[0].length;
                if (x < lesCases.length && y < lesCases[0].length) {
                    lesCases[x][y].setValeur(value);
                    index++;
                } else {
                    // Si nous dépassons les limites de la grille, sortir de la boucle
                    break;
                }
            }
        }
    }

    public Integer getTaille() {
        return taille;
    }

    public AlignementCases getAlignementActif() {
        return alignementActif;
    }

    public Case[][] getLesCases() {
        return lesCases;
    }
}

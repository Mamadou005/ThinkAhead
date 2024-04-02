package modele;

public class Orientation {
    private Integer orientation;

    public Orientation(int i) {
        orientation = i;
    }

    public boolean estVertical(){
        if (orientation == 0){
            return true;
        }
        return false;
    }
    public boolean estHorizontale(){
        if (orientation == 1){
            return true;
        }
        return false;
    }
}

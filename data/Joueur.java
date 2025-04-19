package data;

import java.util.ArrayList;
/**
 * Classe Joueur permetant d'initialiser chaque joueur d'une partie
 */
public class Joueur {
    private String name;
    private ArrayList<Pion> pions;
    private String type;
    
    /**
     * Constructeur de la classe Joueur
     * @param name le nom du Joueur
     * @param type Humain ou Bot
     */
    public Joueur(String name, String type) throws IllegalArgumentException {
        super();
        if (name == null || type == null) {
            String errorMessage = "Le nom et le type du joueur ne peuvent pas être nuls";
            
            throw new IllegalArgumentException(errorMessage);
        }
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Pion> getPions() {
        return pions;
    }

    public void setPions(ArrayList<Pion> pions) throws IllegalArgumentException {
        if (pions == null) {
            
        }
        this.pions = pions;
    }

    public String getType() {
        return type;
    }
}

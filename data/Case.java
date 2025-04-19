package data;

import Configuration.Configuration;
import log.LoggerUtility;
import process.GameManager;

import org.apache.log4j.*;

/**
 * Classe case comportant deux index un pour les colonnes et l'autre pour les lignes, un pion atribue a chaque case et un certain score
 */
public class Case {
    
	private static Logger logger = LoggerUtility.getLogger(GameManager.class, "html");
    private int colonne;
    private int ligne;
    private Pion pion;
    private int score;

    /**
     * Constructeur de la classe Case
     * @param ligne Ordonee de la case
     * @param colonne abscisse de la case
     */
    public Case(int ligne, int colonne) {
        super();
        this.colonne = colonne;
        this.ligne = ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public Pion getPion() {
        return pion;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * verifie si la case est vide
	 * @return la case est elle vide?
	 */
    public boolean isVide() {
        return pion == null;
    }

    @Override
    public String toString() {
        return "Case [ligne=" + ligne + ", colonne=" + colonne + ", pion=" + pion + "]";
    }
    /**
     * Verifie si une case est une case départ en fonction du Joueur
     * @return la case est elle départ?
     * @param joueur le joueur  selectione
     */
    public boolean estCaseDepart(Joueur joueur) {
    	 
    	try {
    		//si le joueur est le bot alors la zone de départ est en haut
            if (ligne < 8 && ligne > 3 && joueur.getType().equalsIgnoreCase("bot") && colonne < 16 && colonne > 8) {
                return true;
            //si le joueur est le joueur humainn alors la zone de départ est en bas
            } else if (ligne > 16 && ligne < 21 && joueur.getType().equalsIgnoreCase("humain") && colonne < 16 && colonne > 8) {
                return true;
            
            } else {
                return false;
            
            }
        } catch (NullPointerException e) {
            logger.error("NullPointerException in estCaseDepart", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in estCaseDepart", e);
            throw e;
        }
    }
    /**
     * Verifie si une case est une case d'arrive en fonction du Joueur
     * @return la case est elle arrive?
     * @param joueur le joueur  selectione
     */
    public boolean estCaseArrive(Joueur joueur) {
        //on vérifie d'abbord que le joueur n'est pas null
    	if (joueur == null) {
            logger.fatal("Joueur est null");
            throw new IllegalArgumentException("Le joueur ne peut pas être null.");
        }
        if (!joueur.getType().equalsIgnoreCase("bot") && !joueur.getType().equalsIgnoreCase("humain")) {
            
            throw new IllegalArgumentException("Le type de joueur doit être 'bot' ou 'humain'.");
        }
        
    	try {
    		//si le joueur est humain alors la zone de d'arrivé est en haut
            if (ligne < 8 && ligne > 3 && joueur.getType().equalsIgnoreCase("humain") && colonne < 16 && colonne > 8) {
                return true;
              //si le joueur est le bot alors la zone de d'arrivé est en bas
            } else if (ligne > 16 && ligne < 21 && joueur.getType().equalsIgnoreCase("bot") && colonne < 16 && colonne > 8) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            logger.error( "NullPointerException in estCaseArrive", e);
            throw e;
        } catch (Exception e) {
            logger.error("Exception in estCaseArrive", e);
            throw e;
        }
    }
    
    /**
     * Verifie si une case est speciale
     * @return si la case est elle speciale
     */
    public boolean estCseSpe() {
        try {
                if (ligne==-1){
                    return false;
                }else if (((Math.abs(ligne-Configuration.LINE_COUNT/2)+ Math.abs(colonne-Configuration.COLUMN_COUNT/2))<9)&&(colonne<8||colonne>16)){
                    return true;
                }else {
                return false;
                }
            
        } catch (Exception e) {
            logger.error( "Exception in estCseSpe", e);
            throw e;
        }
    }

}

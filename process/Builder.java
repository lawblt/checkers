package process;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import log.LoggerUtility;

import Configuration.Configuration;
import data.Case;
import data.Joueur;
import data.Pion;
import data.Plateau;
import Exception.*;
import Gui.Accueil;

/**
 *Cette classe contient les methodes necessaires a la construction d'une partie de jeu de plateau.
 */
public class Builder {

	private static Logger logger = LoggerUtility.getLogger(Builder.class, "html");

	/**
	 * Cette méthode initialise le plateau de jeu avec les dimensions specifiees dans la configuration.
	 * @return le plateau initialise.
	 * @throws ConfigurationException si la configuration est invalide.
	 */
	public static Plateau initPlateau() throws ConfigurationException {
	    logger.info("Initialisation du plateau avec " + Configuration.LINE_COUNT + " lignes et " + Configuration.COLUMN_COUNT + " colonnes.");
	    return new Plateau(Configuration.LINE_COUNT, Configuration.COLUMN_COUNT);
	}

	/**
	 * Cette methode initialise un joueur avec le nom specifie.
	 * @param name le nom du joueur.
	 * @return le joueur initialise.
	 * @throws JoueurException si le nom est invalide.
	 */
	public static Joueur initJoueur(String name) throws JoueurException {
	    if (name == null || name.isEmpty()) {
	        logger.fatal("Le nom du joueur est invalide !");
	        @SuppressWarnings("unused")
	        Accueil accueil=new Accueil();
	        throw new JoueurException("Le nom du joueur est invalide !");
	    }
	    logger.info("Initialisation du joueur avec le nom : " + name);
	    return new Joueur(name,"humain");    
	}

	/**
	 * Cette methode initialise un bot.
	 * @return le joueur initialise en tant que bot.
	 */
	public static Joueur initBot() {
	    logger.info("Initialisation du bot.");
	    return new Joueur("Martin","bot");
	}

	/**
	 * Cette methode construit l'equipe pour les deux joueurs en initialisant les pions.
	 * @param map le plateau de jeu.
	 * @param joueur1 le premier joueur.
	 * @param joueur2 le deuxieme joueur.
	 * @param pions la liste de tous les pions des deux joueurs.
	 * @throws PlateauException si le plateau est invalide.
	 * @throws JoueurException si l'un des joueurs est invalide.
	 */
	public static void buildInitEquipe(Plateau map, Joueur joueur1, Joueur joueur2, ArrayList<Pion> pions) throws PlateauException, JoueurException {
	    logger.info("Construction de l'équipe pour les joueurs : " + joueur1.getName() + " et " + joueur2.getName());
	    initializePion(map, joueur1, pions);
	    initializePion(map, joueur2, pions);
	}

	/**
	 * Cette méthode initialise les pions d'un joueur sur le plateau de jeu.
	 * @param plato le plateau de jeu.
	 * @param joueur le joueur dont on doit initialiser les pions.
	 * @param pions la liste de tous les pions des deux joueurs.
	 * @throws PlateauException si le plateau est invalide.
	 */
    private static void initializePion(Plateau plato, Joueur joueur, ArrayList<Pion> pions) throws PlateauException {
        if (plato == null) {
           
            logger.fatal("Le plateau est manquant !");
            throw new PlateauException("Le plateau est manquant !");
        }
        
        logger.info("Initialisation des pions pour le joueur : " + joueur.getName());
        Case[][] cases = plato.getCases();
        ArrayList<Pion> joueurPions = genererPions(joueur, cases,plato);
        pions.addAll(joueurPions);
        joueur.setPions(joueurPions);
    }
    /**
     *Cette methode genere les pions pour un joueur donne en parcourant toutes les cases
     *du plateau et en ajoutant un pion aux cases de départ du joueur.
     *@param joueur le joueur pour lequel on genere les pions
     *@param cases le tableau representant les cases du plateau
     *@param plato le plateau de jeu
     *@return une liste des pions géneres pour le joueur
     *@throws PlateauException si le tableau de cases ou le plateau est manquant
     */
    private static ArrayList<Pion> genererPions(Joueur joueur, Case[][] cases,Plateau plato) throws PlateauException {
        if (cases == null || plato == null) {
       
            logger.fatal("Le plateau est manquant !");
            throw new PlateauException("Le plateau est manquant !");
        }
        
       
        logger.info("Génération des pions pour le joueur : " + joueur.getName());
       
        ArrayList<Pion> joueurPions = new ArrayList<>();
        
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case c = cases[i][j];
                if (c.estCaseDepart(joueur)) {
                    Pion p = new Pion(c, joueur);
                    c.setPion(p);
                    p.setPosition(c);  
                    joueurPions.add(p);
                }  
            }
        }
        return joueurPions;
    }
}

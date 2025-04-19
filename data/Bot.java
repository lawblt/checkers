package data;


import process.GameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
/**
 * Classe du Bot
 */
public class Bot {
	private boolean pionSelect=false;


	public Bot() throws IllegalArgumentException {
		// TODO Auto-generated constructor stub
	}
	/*
	 * Méthode de déplacement du Bot
	 * @param manager le GameManager assoscié à la partie en cours
	 */
	public void deplacementBot(GameManager manager) {
		//On initialise une ArrayList de pion qui va venir accueillir la liste de pion déplaçable du Bot
		ArrayList<Pion> pionsBotDeplacable = new ArrayList<Pion>();
		//On parcours tous les pions du Bot et on ajoute à l'ArrayList les pions Déplaçables
		for (Pion p : manager.getJoueur2().getPions()) {
			if (manager.estDeplacable(p)){
				pionsBotDeplacable.add(p);
			}
		}
		//Afin de Garder les pions Ranger du plus loin au plus proche on les tries
		if (!pionsBotDeplacable.isEmpty()) {
		trierParOrdonnee(pionsBotDeplacable);
		}
		//Le bot selectionne le pion déplaçable le plus loin
		Pion pion = getFarPion(pionsBotDeplacable);
		manager.setPion(pion);
		Case destination= null;
		int bestScore = Integer.MIN_VALUE;
		//on parcours la liste de deplacablements possibles du bot et on choisi la case avec le score le plus élevé 
		for (Case c :manager.deplacementsPossibles(pion)) {
			if (c.getScore()>bestScore) {
				bestScore=c.getScore();
				destination=c;
			}
		}
		//si le déplacement se fait vers l'avant alors on déplace juste le pion
		if (destination.getLigne()>pion.getPosition().getLigne()){
			manager.movePion(destination);
			bestScore = Integer.MIN_VALUE;
			destination=null;
		//Sinon si le déplacement se fait vers l'arrière on change de pion
		}else if (destination.getLigne()<pion.getPosition().getLigne()){
			// Ici on prends donc un autre pion aléatoirement parmis tous ceux déplaçable sauf le plus loins
			int random = getRandomNumber(1, pionsBotDeplacable.size()-1);
			pion= pionsBotDeplacable.get(random);
			manager.setPion(pion);
			pionSelect=true;
			destination= null;
			bestScore = Integer.MIN_VALUE;
			for (Case ca :manager.deplacementsPossibles(pion)) {
				if (ca.getScore()>bestScore) {
					bestScore=ca.getScore();
					destination=ca;
				}
			}
			//Si le Bot a sélectioné un pion on fait le déplacement
			if(pionSelect) {
				manager.movePion(destination);
				bestScore = Integer.MIN_VALUE;
				destination=null;
			}
		}
		manager.setPionSelectionne(false);
}
	/*
	 * Retourne un entier entre les valeurs saisies
	 * @return l'entier aléatoire
	 * @param min la valeur minimum
	 * @param max la valeur maximum
	 */
	private static int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min + 1) + min;
	}
	/*
	 * Retourne le pion le plus loin parmi une ArrayList de pion
	 * @return le pion le plus loin
	 * @param pions Arraylist de pion (ici celle de pions déplaçable)
	 */
	private static Pion getFarPion(ArrayList<Pion> pions) {
		Pion pion=pions.get(0);
		for (Pion p : pions) {
			if (p.getPosition().getLigne()<pion.getPosition().getLigne()){
				pion=p;
			}
		}
		return pion;
	}
	/*
	 * Méthode qui va trier une Arraylist de pion en fonction de l'ordonée
	 * @param listePions liste de pions à trier 
	 */
	public static void trierParOrdonnee(ArrayList<Pion> listePions) {
	    Collections.sort(listePions, new Comparator<Pion>() {
	      @Override
	      public int compare(Pion p1, Pion p2) {
	        return Integer.compare(p1.getPosition().getLigne(), p2.getPosition().getLigne());
	      }
	    });
	  }

}

package Gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *La classe PartiePanel est une classe qui represente le panneau affiche pendant le jeu contenant les informations sur le joueur courant et l'etat du jeu.
 */
public class PartiePanel extends JPanel {
	
	/**
	 * serialVersionUID permet de garantir que la serialisation est correctement geree entre differentes versions de la classe. 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * joueurCourantLabel est un label qui affiche le nom du joueur courant.
	 */
	private JLabel joueurCourantLabel;

	/**
	 * infoJeu est un label qui affiche des informations sur l'etat du jeu.
	 */
	private JLabel infoJeu;


	/**
	 * Constructeur de la classe PartiePanel.
	 */
	public PartiePanel() {
		super();
		init();
	}

	/**
	 * Methode privée qui permet d'initialiser les labels joueurCourantLabel et infoJeu.
	 */
	private void init() {
		joueurCourantLabel = new JLabel("Joueur courant : ");
		infoJeu = new JLabel("Info : Vous pouvez commencer");
		
		add(joueurCourantLabel);
		add(infoJeu);
		
	}

	/**
	 * Methode qui permet de mettre à jour les labels joueurCourantLabel et infoJeu.
	 * @param joueurCourant le nom du joueur courant.
	 * @param info les informations sur l'etat du jeu.
	 */
	public void updatePanel(String joueurCourant,String info) {
		joueurCourantLabel.setText("Joueur courant : " + joueurCourant);
		infoJeu.setText("Info : "+info);
		
	}

	
}

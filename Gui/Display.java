package Gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import data.*;

import Configuration.Configuration;

import process.GameManager;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Cette classe herite de JFrame et definit un panneau avec une image de fond 
 * ,un bouton "Suivant" pour passer au tour suivant et un bouton "Regles" pour afficher une page contenant les regles . Elle affiche egalement 
 * le plateau de jeu, les pions des joueurs et les messages. 
 * 
 */
public class Display extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Plateau plato;
	private StrategyPaint strategyPaint = new StrategyPaint();
	private GameManager manager;
	private Image backgroundImage;

	
	/**
	 * Constructeur de la classe Display
	 * @param plato : Plateau de jeu
	 * @param manager : GameManager qui gère la partie en cours
	 */
	public Display(Plateau plato,GameManager manager) {
		
		this.manager =manager;
		this.plato = plato;
	} 
	/**
	 * Methode qui dessine le panneau avec les elements de jeu 
	 * @param g : objet Graphics utilise pour dessiner les elements
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Affiche l'image de fond
		g.drawImage(backgroundImage, 0, 0,Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT, null);
		// Charge l'image de fond à partir d'un fichier
		try {
				backgroundImage = ImageIO.read(new File("src/images/backgroundg.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Affiche le plateau de jeu, les pions des joueurs et les messages
		strategyPaint.paint(plato, g);
		strategyPaint.paintJoueur1(manager.getJoueur1(),g);
		strategyPaint.paintJoueur2(manager.getJoueur2(),g);
		// Si un pion est sélectionne, affiche une mise en surbrillance
		if(manager.getPion()!=null) {
			strategyPaint.pionSelect(manager.getPion(), g);
			} 
		// Affiche le bouton "Suivant" pour passer au tour suivant
	    JButton btnSuivant = new JButton("Suivant");
	    btnSuivant.addActionListener(new Gestion()); 
	    btnSuivant.setBounds( Configuration.PLATEAU_HEIGHT-40, Configuration.PLATEAU_HEIGHT-100, 110, 40);
	    
	    JButton btnRegles = new JButton("Règles");
	    btnRegles.addActionListener(new Gestion()); 
	    btnRegles.setBounds(Configuration.PLATEAU_HEIGHT-40, Configuration.WINDOW_HEIGHT-100, 110, 40);
	    
	    this.add(btnRegles);
	    this.add(btnSuivant);
	    this.setLayout(null);
	    this.setVisible(true);		
			
	}
	/**
	 * Classe interne qui implémente ActionListener pour gerer l'action du bouton "Suivant"
	 */
	public class Gestion implements ActionListener {

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		if(e.getActionCommand().equals("Suivant")) {
    			if(manager.getNbTour()%2!=0) {
    				manager.setPionSelectionne(false);
    				manager.setPremierTour(true);
        			manager.setNbTour(manager.getNbTour()+1);
        			manager.getChevauch().clear();
        			manager.prochainTour();
        			manager.tourDuBot();
    			}
    		}
    			else if(e.getActionCommand().equals("Règles")) {
    			@SuppressWarnings("unused")
				ReglesGui regles=new ReglesGui();
    			
    			}else {
				manager.setMessage("Selectionner un Pion");
    		}
    		}

	}
	/**
	 *Cette methode renvoie la case du plateau correspondant aux coordonnees (x,y) passées en parametres.
	 *Elle parcourt également la liste des pions geree par le GameManager pour trouver le pion correspondant a la case trouvee et l'assigner a la case.
	 *@param x : la coordonnee en x
	 *@param y : la coordonnee en y
	 *@return la case correspondant aux coordonnées (x,y)
	 */
	public Case getPionPosition(int x, int y) {
		int line = y / Configuration.BLOCK_SIZE;
		int column = x / Configuration.BLOCK_SIZE;
		Case ca=plato.getCase(line, column);
		for(Pion p:manager.getPions()) {
			if(p.getPosition().getLigne()==ca.getLigne()&&p.getPosition().getColonne()==ca.getColonne()) {
				ca.setPion(p);
			}
		}
		return ca;
	}
	
	
}
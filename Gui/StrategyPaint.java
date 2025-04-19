package Gui;

import data.Case;

import java.awt.Color;
import java.awt.Graphics;

import Configuration.Configuration;
import data.Joueur;
import data.Pion;
import data.Plateau;

/**
 * Classe permettant la peronalisation de notre application
 */
public class StrategyPaint {
	
	/**
	 * Dessine le plateau de jeu à partir d'un objet Plateau et d'un objet Graphics.
	 * @param map l'objet Plateau qui represente le plateau de jeu.
	 * @param g l'objet Graphics sur lequel dessiner le plateau.
	 */
	public void paint(Plateau map, Graphics g) {
		
		int WindowSize= Configuration.WINDOW_WIDTH;
		
		g.setColor(Color.decode("#44362F"));
		
		int[] x={WindowSize/2,WindowSize/8,WindowSize/2,WindowSize*7/8};
		int[] y={WindowSize/8,WindowSize/2,WindowSize*7/8,WindowSize/2};
		
	   
		g.drawPolygon(x,y,x.length);
		g.fillPolygon(x,y,x.length);
		g.setColor(Color.decode("#9F8170"));
		
		int[] z= {WindowSize/8,WindowSize*41/128,WindowSize*41/128};
	    int[] t= {WindowSize*1/2,WindowSize*39/128,WindowSize*89/128};

	    g.drawPolygon(z,t,z.length);
	    g.fillPolygon(z,t,z.length);

	    int[] v= {WindowSize*7/8,WindowSize*87/128,WindowSize*87/128};
	    int[] w= {WindowSize*1/2,WindowSize*39/128,WindowSize*89/128};
	    g.drawPolygon(v,w,v.length);
	    g.fillPolygon(v,w,v.length);
	 
		int caseSize = Configuration.BLOCK_SIZE;
		
		Case[][] block= map.getCases();
		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColomnCount(); columnIndex++) {
				Case c = block[lineIndex][columnIndex];
				g.setColor(Color.decode("#aba495"));
				g.fillOval(c.getColonne() * caseSize, c.getLigne() * caseSize, caseSize, caseSize);
			}
		}

	}

	/**
	 * Dessine les pions du joueur 1 à partir d'un objet Joueur et d'un objet Graphics.
	 * @param j l'objet Joueur qui represente le joueur 1.
	 * @param g l'objet Graphics sur lequel dessiner les pions.
	 */
	public void paintJoueur1(Joueur j, Graphics g) {
	    int pionSize = Configuration.BLOCK_SIZE;
	    g.setColor(Color.decode("#DEB887"));
	    for (Pion pion : j.getPions()) {
	        Case position = pion.getPosition();
	        int x = position.getColonne();
	        int y = position.getLigne();
	        g.fillOval(x * pionSize, y * pionSize, pionSize, pionSize);
	    }
	}

	/**
	 * Dessine les pions du joueur 2 à partir d'un objet Joueur et d'un objet Graphics.
	 * @param j l'objet Joueur qui represente le joueur 2.
	 * @param g l'objet Graphics sur lequel dessiner les pions.
	 */
	public void paintJoueur2(Joueur j, Graphics g) {
	    int pionSize = Configuration.BLOCK_SIZE;
	    g.setColor(Color.black);
	    for (Pion pion : j.getPions()) {
	        Case position = pion.getPosition();
	        int x = position.getColonne();
	        int y = position.getLigne();
	        g.fillOval(x * pionSize, y * pionSize, pionSize, pionSize);
	        
	    }
	    
	}
	/**
	 *Cette méthode est utilisee pour afficher un pion selectionne sur le plateau.
	 *@param p le pion a afficher
	 *@param g l'objet Graphics utilise pour dessiner le pion sur le plateau
	 */
	public void pionSelect(Pion p, Graphics g) {
		 int pionSize = Configuration.BLOCK_SIZE;
		 g.setColor(Color.decode("#808000"));
		 int x = p.getPosition().getColonne();
	     int y = p.getPosition().getLigne();
	     g.fillOval(x * pionSize, y * pionSize, pionSize, pionSize);
	}
}

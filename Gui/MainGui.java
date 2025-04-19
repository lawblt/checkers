package Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import org.apache.log4j.Logger;

import process.*;
import Configuration.Configuration;
import Exception.ConfigurationException;
import data.*;
import log.LoggerUtility;
/**
 * clase main de la Jframe de la partie
 */
public class MainGui extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 5193610496520599151L;

	private PartiePanel partiePanel;
	private Plateau plato;    
	private GameManager manager;
	private static Logger logger = LoggerUtility.getLogger(GameManager.class, "html");
	private final static Dimension preferredSize = new Dimension(Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT);


	private Display dashboard;
	
	/**
	 * Constructeur de la classe MainGui
	 */
	public MainGui() {
		super();
		init();
		this.setLocationRelativeTo(null); //la fenetre apparait au centre de l'ecran

		partiePanel = new PartiePanel(); //instanciation de PartiePanel
		partiePanel.setVisible(true);
		add(partiePanel, BorderLayout.NORTH); //ajout de PartiePanel a la fenetre principale

	}

	/**
	 * Initialise la fenetre principale
	 */
	private void init() {
		Container contentPane = getContentPane(); //recuperation du conteneur de la fenetre
		contentPane.setLayout(new BorderLayout()); //definition d'un layout pour la fenetre
		
		try {
			plato= Builder.initPlateau(); //initialisation du plateau
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		manager = new GameManager(); //instanciation du gestionnaire de jeu
		dashboard = new Display(plato,manager); //instanciation du panneau qui affiche le plateau
		
		MouseControls mouseControls = new MouseControls(); //instanciation du gestionnaire d'événements de souris
		dashboard.addMouseListener(mouseControls); //ajout du gestionnaire d'événements de souris au panneau
		
		dashboard.setPreferredSize(preferredSize); //définition de la taille préférée du panneau
		contentPane.add(dashboard, BorderLayout.CENTER); //ajout du panneau à la fenêtre principale

		setDefaultCloseOperation(EXIT_ON_CLOSE); //définition de l'action à effectuer lorsque la fenêtre est fermée
		pack(); //ajustement de la taille de la fenêtre en fonction de son contenu
		
		setVisible(true); //affichage de la fenêtre
		setPreferredSize(preferredSize); //définition de la taille préférée de la fenêtre
		setResizable(false); //interdiction de redimensionner la fenêtre
		
	}    

	/**
	 * Methode permettant de lancer la partie dans un thread separe
	 */
	@Override
	public void run() {
		while (true) { //boucle infinie
			try {
				Thread.sleep(Configuration.GAME_SPEED); //pause pour rafraîchir l'affichage
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

			dashboard.repaint();
			partiePanel.updatePanel(manager.getJoueurCourant().getName(),manager.getMessage()); 
			
		}
	}
	/**
	 Classe interne pour gzrer les evenements de souris.
	 */
	private class MouseControls implements MouseListener {
		Pion p;
		Case positionDepart;
		
		boolean isSimpleClickProcessed = false; // variable pour indiquer si le simple clic a déjà été pris en compte
		
		/**
		 *Methode pour traiter l'evenement de clic de la souris.
		 *@param e l'evenement de clic de la souris
		 */
		public void mouseClicked(MouseEvent e) {
			if (!manager.isPionSelectionne()) { 
				if(e.getClickCount()==1 && !isSimpleClickProcessed) {
			        int x=e.getX();  
			        int y=e.getY();

			        positionDepart = dashboard.getPionPosition(x,y); 
			       
			        for(Pion p1:manager.getPions()) {
			            if(p1.getPosition().getLigne()==positionDepart.getLigne() && p1.getPosition().getColonne()==positionDepart.getColonne()) {
			                if(p1.getJoueur()==manager.getJoueurCourant()) {
			                    p=p1;
			                    manager.setPion(p);
			                }
			            } 
			         }
			        if(p!=null) {
			                    if(p.getJoueur() !=manager.getJoueurCourant()|| !manager.estDeplacable(p)) {
			         
			                    	manager.setMessage("Vous ne pouvez pas sélectionner ce pion !");
				                    logger.warn("Vous ne pouvez pas séléclionner ce pion");
				                    
				                    manager.setPionSelectionne(false); 
			                    	//mouseClicked(e);
			                    	//return;
			                    }else {
			                    manager.setNbTour(manager.getNbTour()+1);
			                    manager.setPionSelectionne(true); 
						        isSimpleClickProcessed = true; // marquer le simple clic comme étant déjà pris en compte

			            	  }
				}   
				}
			       
		 } else {
	
			    if (e.getClickCount() == 2) {
			        int x=e.getX();
			        int y=e.getY();
	
			        Case pionPosition = dashboard.getPionPosition(x,y);
			       
			        for(Case c:manager.deplacementsPossibles(positionDepart.getPion())) {
			            if(c.getLigne()==pionPosition.getLigne()&&c.getColonne()==pionPosition.getColonne()) {
			            	if(manager.isPremierTour()) {
			            		manager.movePion(pionPosition);
			            		manager.setPremierTour(false);
			            
			            	}else {
			            		 for(Case ch:manager.getChevauch()){
			 			            if(ch.getLigne()==pionPosition.getLigne()&&ch.getColonne()==pionPosition.getColonne()) {
			 			            		manager.movePion(pionPosition);
			 			            }
			            		 }
			            	}
			            	
			               if( manager.joueurAGagne(manager.getJoueurCourant())) {
			            	   dispose();
			            	   new FinDePartieGui();
			            	   //fin de la partie a organiser
			               }
			           }
			       }
			       
			       isSimpleClickProcessed = false; // remettre le simple clic à false pour le prochain clic*/
			    }
			    partiePanel.updatePanel(manager.getJoueurCourant().getName(),manager.getMessage()); 
		 	}	   
		}

		
		 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
			
			
	}
}
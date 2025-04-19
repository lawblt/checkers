package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Configuration.Configuration;

/**
 * Classe de la page d'indication de fin de partie.
 */
public class PresentationGui extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image backgroundImage;

	/**
	 * Constructeur de la classe PresentationGui
	 * Initialise les elements graphiques de la fenetre de presentation.
	 * Les boutons sont relies a des ActionListener qui permettent la navigation entre les differentes pages de l'application.
	 */
	public PresentationGui() {
		super();
		
		// Creation du panneau principal
		contentPane = new JPanel() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(backgroundImage, 0, 0,Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT, null);

	        }
	    };
	    
	    // Ajout du fond d'ecran
	    try {
	        backgroundImage = ImageIO.read(new File("src/images/plato.jpg"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    setContentPane(contentPane);
	    contentPane.setLayout(null);
		
		// Ajout du titre
		JLabel label = new JLabel("Indication de debut de manche");
		label.setFont(new Font("Serif", Font.BOLD, 20));
		label.setForeground(Color.black);
		label.setBounds((Configuration.WINDOW_HEIGHT)*1/2-125, (Configuration.WINDOW_WIDTH)/16, 400,50);
		add(label);
		
		// Ajout du texte d'explication
		JTextArea regles= new JTextArea("\n Bienvenue dans une nouvelle partie de dame chinoise.\n Réussirez-vous à battre Martin, réputé comme imbattable sur cette plateforme ?\n Soyez stratégique, rapide et astucieux, et nous l'espérons chanceux !\n Bon courage... \r\n\n Quelques mots sur le fonctionnement:\n\n -> La sélection d'un pion s'effectue en cliquant sur celui-ci.\n\n -> Le déplacement vers une autre case s'effectue en double cliquant sur la case de destination.\n\n Attention: une fois séléctionné,vous ne pouvez plus changer d'avis...Faites le bon choix.\n -> Une fois le tour du joueur est passé, appuyer sur le bouton 'suivant' afin de valider votre tour.\n\n Avant de lancer une partie, assurez-vous de bien connaître les règles et les différentes stratégies du jeu.\r\n\n Cliquez sur le bouton 'Règle' pour visualiser les règles du jeu.\r\n");
	    regles.setFont(new Font("Serif",20,15));
	    regles.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	    regles.setLineWrap(true);
	    regles.setEditable(false);
	    regles.setWrapStyleWord(true);

     regles.setBounds((Configuration.WINDOW_WIDTH)/8, (Configuration.WINDOW_HEIGHT)/8,(Configuration.WINDOW_WIDTH)*6/8,(Configuration.WINDOW_HEIGHT)*6/8);
     add(regles);
	
	JButton btnJouer = new JButton("Suivant");
    btnJouer.addActionListener(new Gestion()); 
    btnJouer.setBounds(Configuration.WINDOW_WIDTH-110,0, 110, 40);
    //Ajouter le bouton au frame
    this.add(btnJouer);
    this.setLayout(null);
    this.setVisible(true); 
    
   
    
    JButton btnAccueil = new JButton("Précédant");
    btnAccueil.addActionListener(new Gestion()); 
    btnAccueil.setBounds(0, 0, 110, 40);
    //Ajouter le bouton au frame
    this.add(btnAccueil);
    this.setLayout(null);
    this.setVisible(true);
    
    JButton btnRegles = new JButton("Règles");
    btnRegles.addActionListener(new Gestion()); 
    btnRegles.setBounds((Configuration.WINDOW_WIDTH)*1/2-50, (Configuration.WINDOW_HEIGHT)*7/8+5, 100, 40);
    //Ajouter le bouton au frame
    this.add(btnRegles);
    this.setLayout(null);
    this.setVisible(true);
    
	
	
    this.setSize(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo( null );
    this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    this.setVisible(true);
    
	}

	/**
	 *La classe Gestion implemente l'interface ActionListener pour gerer les actions des boutons de l'interface graphique.
	 */
	public class Gestion implements ActionListener {

	    	@Override
	    	/**
	    	 *La methode actionPerformed() permet de definir les actions a effectuer en fonction du bouton clique.
	    	 *@param e un evenement d'action, tel que la selection d'un bouton.
	    	 */
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		if(e.getActionCommand().equals("Suivant")) {
	    			dispose();
	    			MainGui gameMainGUI = new MainGui();
	    			Thread gameThread = new Thread(gameMainGUI);
	    			gameThread.start();
	    		}else if(e.getActionCommand().equals("Précédant")) {
	    			dispose();
	    			@SuppressWarnings("unused")
					Accueil accueil=new Accueil();
	    		}else if(e.getActionCommand().equals("Règles")) {
	    			@SuppressWarnings("unused")
					ReglesGui regles=new ReglesGui();
	    		}

	    	}

	    }

}
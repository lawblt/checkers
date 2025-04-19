package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import Configuration.Configuration;

/**
 * Classe de la page de regles du jeu.
 */
public class ReglesGui extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image backgroundImage;

	/**
	 * Constructeur de la classe ReglesGui.
	 * Initialise l'interface graphique et ajoute les elements tels que le titre, les regles et les boutons.
	 * @throws HeadlessException
	 */
	public ReglesGui() throws HeadlessException {
		super();
		
		// Créer un JPanel pour definir l'image de fond.
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
	    try {
	        backgroundImage = ImageIO.read(new File("src/images/plato.jpg"));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    // Ajouter les elements à l'interface graphique.
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

		JLabel titre = new JLabel("Règes du jeu");
	    titre.setFont(new Font("Serif", Font.BOLD, 20));
	    titre.setForeground(Color.black);
	    titre.setBounds((Configuration.WINDOW_HEIGHT)*1/2-75, (Configuration.WINDOW_WIDTH)/16, 200,50);
	    add(titre);

        JTextArea regles= new JTextArea(" Le but est simple, il suffit d’être le plus rapide à déplacer ses pions sur la base qui se trouve en face de celle de départ. \r\n"
                + "Pour se faire, il suffit de déplacer « tour par tour » ses pions de façon stratégique et astucieuse.\r\n"
                + "\r\n"
                + "Néanmoins, il n’est pas possible de les déplacer de manière aléatoire. En effet, ceux-ci se déplacent "
                + "uniquement d’une case en diagonale ou en chevauchant les pions (alliés ou ennemis) qui se trouvent sur "
                + "des diagonales et cela, de façon illimitée, à condition qu’après chaque chevauchement il y ait une place "
                + "vide. \r\n"
                + "\r\n"
                + "Cela signifie, qu’il est impossible de chevaucher deux pions diagonalement côte à côte. La partie se "
                + "termine lorsque tous les pions, d’une même couleur, se retrouvent dans la base opposé à celle de départ.");
        regles.setFont(new Font("Serif",20,15));
        regles.setLineWrap(true);
        regles.setEditable(false);
        regles.setWrapStyleWord(true);
        regles.setBounds((Configuration.WINDOW_WIDTH)/8, (Configuration.WINDOW_HEIGHT)/8,(Configuration.WINDOW_WIDTH)*6/8,(Configuration.WINDOW_HEIGHT)*6/8);
        add(regles);

		this.setLayout(null);
	    this.setSize(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	    this.setResizable(false);
	    this.setLocationRelativeTo( null );
	    this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	    this.setVisible(true);
	    
	    
		}


}
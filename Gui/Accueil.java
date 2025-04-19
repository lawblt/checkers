package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import Configuration.Configuration;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

	
/**
 * Cette classe est la page d'accueil et herite de JFrame et definit un panneau avec une image de fond 
 * ,un bouton "Jouer" permettant d'acceder a la page d'indication et un bouton regle permettant d'acceder a  
 * la page de regles.
 */
public class Accueil extends JFrame implements ActionListener {
	
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Image backgroundImage;
	
    /**
     * Constructeur de la classe Accueil
     */
	public Accueil(){
		
		super( "Checkers" );
		

	    contentPane = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			/**
			 * @param g composant graphique qui va servir de support
			 */
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
        setContentPane(contentPane);
        
        JLabel label = new JLabel("Checkers");
    	label.setFont(new Font("Abril Fatface", Font.BOLD, 40));
    	label.setForeground(Color.black);
    	label.setBounds((Configuration.WINDOW_HEIGHT)*1/2-90, (Configuration.WINDOW_WIDTH)*1/5, 400,50);
    	add(label);
    	
        contentPane.setLayout(null);
	    this.setSize(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	    this.setResizable(false);
	    this.setLocationRelativeTo( null );
	    this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	    

	   JButton btnJouer = new JButton("Jouer");
	    btnJouer.addActionListener(new Gestion()); 
	    btnJouer.setBounds((Configuration.WINDOW_WIDTH)*1/5, (Configuration.WINDOW_HEIGHT)*3/7, 100, 40);
	    //Ajouter le bouton au frame
	    this.add(btnJouer);

	    
	   
	    
	    JButton btnRegles = new JButton("Règles");
	    btnRegles.addActionListener(new Gestion()); 
	    btnRegles.setBounds((Configuration.WINDOW_WIDTH)*2/3, (Configuration.WINDOW_HEIGHT)*3/7, 100, 40);
	    //Ajouter le bouton au frame
	    this.add(btnRegles);
	    this.setLayout(null);
	    this.setVisible(true);
	    
		}
	
		/**
		 * Constructeur de la classe Gestion classe fille de la classe ActionListener
		 */
	    public class Gestion implements ActionListener {

	    	@Override
	    	/**
	    	 * Methode permettant de faire des actions sur la Jframe
	    	 * @param e action effectue
	    	 */
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		// TODO Auto-generated method stub
	    		if(e.getActionCommand().equals("Règles")) {
	    			try {
						@SuppressWarnings("unused")
						ReglesGui regles=new ReglesGui();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}else if(e.getActionCommand().equals("Jouer")) {
	    			dispose();
	    			@SuppressWarnings("unused")
					PresentationGui presentation=new PresentationGui();
	    		}
	    		
	    	}

	    }
	/**
	 * main
	 */
	public static void main(String[] args) {
		new Accueil().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
package Gui;


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
import javax.swing.JPanel;

	
/**
 *Cette classe represente la fenetre de fin de partie.
 */
public class FinDePartieGui  extends JFrame implements ActionListener {
	
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Image backgroundImage;
	
    /**
     *Constructeur de la classe FinDePartieGui.
     */
	public FinDePartieGui(){
		
		super( "Checkers" );
		

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
            backgroundImage = ImageIO.read(new File("src/images/vic.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(contentPane);
        
        contentPane.setLayout(null);
	    this.setSize(Configuration.WINDOW_WIDTH,Configuration.WINDOW_HEIGHT);
	    this.setResizable(false);
	    this.setLocationRelativeTo( null );
	    this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
	    

	   JButton btnJouer = new JButton("Rejouer");
	    btnJouer.addActionListener(new Gestion()); 
	    btnJouer.setBounds((Configuration.WINDOW_WIDTH)*1/5, (Configuration.WINDOW_HEIGHT)*5/7, 110, 40);
	    //Ajouter le bouton au frame
	    this.add(btnJouer);

	    
	   
	    
	    JButton btnRegles = new JButton("Règles");
	    btnRegles.addActionListener(new Gestion()); 
	    btnRegles.setBounds((Configuration.WINDOW_WIDTH)*2/3, (Configuration.WINDOW_HEIGHT)*5/7, 110, 40);
	    //Ajouter le bouton au frame
	    this.add(btnRegles);
	    this.setLayout(null);
	    this.setVisible(true);
	    
		}
	
		/**
		 *Cette classe interne permet de gerer les actions des boutons "Rejouer" et "Regles".
		 */
	    public class Gestion implements ActionListener {

	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		// TODO Auto-generated method stub
	    		// TODO Auto-generated method stub
	    		if(e.getActionCommand().equals("Règles")) {
	    			dispose();
	    			try {
						@SuppressWarnings("unused")
						ReglesGui regles=new ReglesGui();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}else if(e.getActionCommand().equals("Rejouer")) {
	    			dispose();
	    			@SuppressWarnings("unused")
					PresentationGui presentation=new PresentationGui();
	    		}
	    		
	    	}

	    }
 
	public static void main(String[] args) {
		new FinDePartieGui().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}



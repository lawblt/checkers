package Gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * classe permetant d'afficher un fond dans une Jframe
 */
public class BackgroundImageJFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private Image backgroundImage;
    /**
     * Constructs a new BackgroundImageJFrame object.
     * 
     * Sets the bounds of the JFrame to (100, 100, 450, 300), sets the default close operation
     * to EXIT_ON_CLOSE, and sets the JPanel's layout to null. Reads in an image file 
     * "checkers/images/halma.jpg" to be used as the background image of the JPanel. 
     * Overrides the paintComponent method of the JPanel to paint the background image onto the JPanel.
     */
    public BackgroundImageJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        try {
            backgroundImage = ImageIO.read(new File("checkers/images/halma.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    /**
     * Main method for testing the BackgroundImageJFrame class.
     */
    public static void main(String[] args) {
        BackgroundImageJFrame frame = new BackgroundImageJFrame();
        frame.setVisible(true);
    }
}
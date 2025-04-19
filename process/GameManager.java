package process;

import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;
import org.apache.log4j.*;

import Configuration.Configuration;
import Exception.ConfigurationException;
import Exception.JoueurException;
import Exception.PlateauException;

import data.*;
import log.LoggerUtility;


/**
*La classe GameManager gere le deroulement d'une partie du jeu de PionMagique.
*Elle est responsable de la creation et de la mise à jour du plateau, des joueurs,
*des pions, et de la gestion du tour par tour.
*/
public class GameManager {
	
	private static Logger logger = LoggerUtility.getLogger(GameManager.class, "html");
	private String message="vous pouvez commencer !";
	private Plateau plato;
	private ArrayList<Pion> pions;
	private ArrayList<Case>cases;
	private Pion pion=null;
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurCourant;
	private boolean pionSelectionne = false;
	private Bot bot=new Bot();
	private int nbTour=0;
	private boolean premierTour=true;
	private ArrayList<Case>chevauch;
	private List<Case> zoneArr = new ArrayList<Case>();


	/**
	 * Constructeur de GameManager qui cree un plateau, initialise le joueur1 avec le nom saisi,
	 * et construit l'equipe initiale de chaque joueur. 
	 * Si la configuration du plateau echoue, une exception RuntimeException sera levee.
	 * Si l'initialisation de joueur1 echoue, une fenetre d'erreur sera affichee et le programme se terminera.
	 */
	public GameManager() {
		try {
			plato = Builder.initPlateau();
		} catch (ConfigurationException e) {
			logger.error("Impossible d'initialiser le plateau", e);
			throw new RuntimeException("Erreur lors de l'initialisation du plateau", e);
		}
		String name = JOptionPane.showInputDialog(null, "Entrez votre nom :", "Nom", JOptionPane.PLAIN_MESSAGE);
		boolean initialisationReussie = false;
		
		while (!initialisationReussie){
		    try {
		        joueur1 = Builder.initJoueur(name);
		        initialisationReussie = true;
		    } catch (JoueurException e) {
		        logger.error("Impossible d'initialiser le joueur 1", e);
		       JOptionPane.showMessageDialog( null, "Erreur lors de l'initialisation du joueur 1.","message", JOptionPane.INFORMATION_MESSAGE);
		       System.exit(0);
		        
		    }
		} 
		joueur2 = Builder.initBot();
		pions = new ArrayList<Pion>();
		cases = new ArrayList<Case>();
		chevauch=new ArrayList<Case>();
		joueurCourant = joueur1;
		try {
			Builder.buildInitEquipe(plato, joueur1, joueur2, pions);
		} catch (PlateauException | JoueurException e) {
			logger.error("Impossible de construire l'équipe initiale", e);
			throw new RuntimeException("Erreur lors de la construction de l'équipe initiale", e);
		}

	}

	/**
	 * Renvoie true si le premier tour est en cours, false sinon.
	 * @return vrai si c'est le premier tour, faux sinon
	 */
	public boolean isPremierTour() {
		return premierTour;
	}

	/**
	 * Définit si c'est le premier tour ou non.
	 * @param premierTour true si c'est le premier tour, false sinon
	 */
	public void setPremierTour(boolean premierTour) {
		this.premierTour = premierTour;
	}

	/**
	 * Renvoie la liste des cases chevauchees par un pion.
	 * @return la liste des cases chevauchees par un pion
	 */
	public ArrayList<Case> getChevauch() {
		return chevauch;
	}




	public void setChevauch(ArrayList<Case> chevauch) {
		this.chevauch = chevauch;
	}




	public Plateau getPlato() {
		return plato;
	}
	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
	
	public boolean isPionSelectionne() {
		return pionSelectionne;
	}

	public void setPionSelectionne(boolean pionSelectionne) {
		this.pionSelectionne = pionSelectionne;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 *Cette methode permet d'obtenir la liste des cases du plateau.
	 *@return une ArrayList d'objets Case.
	 */
	public ArrayList<Case> getCases(){
		for(int i=0;i<plato.getLineCount();i++) {
			for(int j=0;j<plato.getColomnCount();j++) {
				Case c=plato.getCase(i, j);
				if(!cases.contains(c)&&c.getLigne()!=-1&&c.getColonne()!=-1) {
					cases.add(c);
				}
				
			}
			
		}
		return cases;
	}
	/**
	 *Cette methode permet d'obtenir la liste des pions sur le plateau.
	 *@return une ArrayList d'objets Pion.
	 */
	public ArrayList<Pion> getPions() {
		for(Pion p1:joueur1.getPions()) {
			if(pions.contains(p1)) {
				continue;
			}else {
			pions.add(p1);
		}
		}
		for(Pion p2:joueur2.getPions()) {
			if(pions.contains(p2)) {
				continue;
			}else {
		
			pions.add(p2);
			}
		}
		return pions;
	}

	
	public Pion getPion() {
		return pion;
	}


	public void setPion(Pion pion) {
		this.pion = pion;
	}


	public Joueur getJoueur1() {
		return joueur1;
	}


	public Joueur getJoueur2() {
		return joueur2;
	}


	public Joueur getJoueurCourant() {
		return joueurCourant;
	}


	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}


	

	/**
	 * Renvoie une liste de toutes les cases d'arrivee possibles pour le joueur donne.
	 * @param joueur le joueur dont on veut obtenir les cases d'arrivee possibles.
	 * @return une liste de toutes les cases d'arrivee possibles pour le joueur donne.
	 */
	public List<Case> getZoneArr(Joueur joueur) {
	    for(Case c:getCases()) {
	        if(c.estCaseArrive(joueur)) {
	            zoneArr.add(c);
	        }
	    }
	    return zoneArr;
	}

	/**
	 * Ajoute une case d'arrivee à la liste des cases d'arrivee.
	 * @param c la case d'arrivee à ajouter.
	 */
	public void addArr(Case c) {
	    zoneArr.add(c);
	}

	/**
	 * Passe au tour suivant en changeant le joueur courant et en mettant a jour le message.
	 */
	public void prochainTour() {
	    if (joueurCourant == joueur1) {
	        joueurCourant = joueur2;
	        message = joueur2.getName() + " à vous de jouer";
	        logger.trace("Le joueur 2 a été choisi pour le prochain tour");
	    } else {
	        joueurCourant = joueur1;
	        message = joueur1.getName() + " à vous de jouer";
	        logger.trace("Le joueur 1 a été choisi pour le prochain tour");
	    }
	}

	/**
	 * Vérifie si le pion donné peut être déplacé sur le plateau.
	 * @param pion le pion à vérifier.
	 * @return true si le pion peut être déplacé, false sinon.
	 */
	public boolean estDeplacable(Pion pion) {
	    if (pion==null){
	        return false;
	    }else{
	        return(!deplacementsPossibles(pion).isEmpty());
	    }
	}

	/**
	 * Renvoie une liste de toutes les cases sur lesquelles le pion donne peut etre déplace.
	 * @param pion le pion pour lequel on veut obtenir les cases de deplacement possibles.
	 * @return une liste de toutes les cases sur lesquelles le pion donne peut etre déplace.
	 * @throws NullPointerException si le pion donne est null.
	 */
	public List<Case> deplacementsPossibles(Pion pion) throws NullPointerException {

	    List<Case> casesPossibles = new ArrayList<>();

	    if(pion!=null) {

	        int x = pion.getPosition().getLigne();
	        int y = pion.getPosition().getColonne();

	        // Verifier les déplacements dans les 4 directions possibles
	        for (int dx = -1; dx <= 1; dx += 2) {
	            for (int dy = -1; dy <= 1; dy += 2) {
	                if (dx == 0 && dy == 0) {
	                   continue;
	                }

	                int newX = x + dx;
	                int newY = y + dy;
	                Case caseDest=plato.getCase(newX,newY);

	                for(Pion p:getPions()) {
	                    if(p.getPosition().getLigne()==caseDest.getLigne()&&p.getPosition().getColonne()==caseDest.getColonne()) {
	                        caseDest.setPion(p);
	                    }
	                }

	                // Verifier si la case de destination est valide
	                if (isValidCase(caseDest)) {
	                    if (caseDest.isVide()) {
	                        casesPossibles.add(caseDest);

	                    } else {  
			            
			                    // Verifier les déplacements de 2 cases dans cette direction
			                    
			                	caseDest= plato.getCase(newX + dx, newY + dy);
			                	for(Pion p:getPions()) {
			    	    			if(p.getPosition().getLigne()==caseDest.getLigne()&&p.getPosition().getColonne()==caseDest.getColonne()) {
			    	    				caseDest.setPion(p);
			    	    			}  
			    	    		}
			                        if (isValidCase(caseDest)
			                                && caseDest.isVide()) {
			                        	
			                            casesPossibles.add(caseDest);
			                            if(!premierTour) {
					                    	chevauch.add(caseDest);
					                    }
			                        }       
			                        else {  
			    			            
					                    // Verifier les déplacements de 3 cases dans cette direction
					                    
					                	caseDest= plato.getCase(newX + dx*2, newY + dy*2);
					                	if(caseDest.estCseSpe()) {
					                	
						                	for(Pion p:getPions()) {
						    	    			if(p.getPosition().getLigne()==caseDest.getLigne()&&p.getPosition().getColonne()==caseDest.getColonne()) {
						    	    				caseDest.setPion(p);
						    	    			}  
						    	    		}
						                        if (isValidCase(caseDest)
						                                && caseDest.isVide()) {
						                        	
						                             casesPossibles.add(caseDest);
						                             if(!premierTour) {
									                    	chevauch.add(caseDest);
									                    }
						                        }
						                     }
					                	}
			                        }
			               }
			        } 	}
			    }
			    
			    return casesPossibles; 	
		
	}
	/**
	 *Verifie si la case donnée est valide.
	 *@param c la case à verifier
	 *@return true si la case est valide, false sinon
	 */
	public boolean isValidCase(Case c) {
        if (c.getLigne()==-1){
            return false;
        }else{
            return (Math.abs(c.getLigne()-Configuration.LINE_COUNT/2)+ Math.abs(c.getColonne()-Configuration.COLUMN_COUNT/2))<9;
        }
    }
	
	/**
	 *Deplace le pion sur la case donnee.
	 *@param c la case sur laquelle deplacer le pion
	 */
	public void movePion(Case c) {
		try {
		    Case position = pion.getPosition();
		    position.setPion(null);
		    
		    Case newPosition = plato.getCase(c.getLigne(),c.getColonne());
		    
		    boolean foundPion = false;
		    for(Pion p: pion.getJoueur().getPions()) {
		        if(p==pion) {
		            p.setPosition(newPosition);
		            foundPion = true;
		            break;
		        }
		    } 

		    if (!foundPion) {
		    	
		    	message="Le pion n'appartient pas au joueur";
		    	logger.error("Le pion " + pion + " n'appartient pas au joueur " + pion.getJoueur());
		        throw new IllegalArgumentException("Le pion n'appartient pas au joueur.");
		    }else {
		   
				c.setPion(pion);
				
		    }
		}catch(NullPointerException e) {
			message="choisissez un nouveau pions";
			setPionSelectionne(false); 
		}
	}
	/**
	 *Fait jouer le bot et passe au prochain tour.
	 */
	public void tourDuBot() {
		bot.deplacementBot(this);
		prochainTour();
		
	}
	/**
	 *Verifie si le joueur donné a gagne la partie.
	 *@param joueur le joueur à verifier
	 *@return true si le joueur a gagne, false sinon
	 */
	public boolean joueurAGagne(Joueur joueur) {
	    for (Pion pion : joueur.getPions()) {
	        if (!getZoneArr(joueur).contains(pion.getPosition())) {
	            // Le pion n'est pas dans la zone d'arrivee, le joueur n'a pas gagne
	            return false;
	        }
	    }
	    message=joueur.getName()+" à gagné la partie";
	    // Tous les pions du joueur sont dans la zone d'arrivee, le joueur a gagne
	    return true;
	}
}



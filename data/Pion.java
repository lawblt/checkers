package data;

/**
 * Classe Pion
 */
public class Pion {
	private Case position;
    private Joueur joueur;
 
    private boolean estArrive;
	
    
    /**
     * Constructeur de la classe Pion
     * @param position  la case assoscié au pion
     * @param joueur le joueur à qui appartient le pion
     */
	public Pion(Case position,Joueur joueur) {
		super();
		this.position = position;
		this.joueur = joueur;
		this.estArrive = false;
	}

	



	public Case getPosition() {
		return position;
	}
	
	public void setPosition(Case position) {
	    this.position = position;
	}



	public Joueur getJoueur() {
		return joueur;
	}


	public boolean isEstArrive() {
		return estArrive;
	}

	//vérifie si un pion est dans la zone spéciale
	public boolean estDansLaZoneSpe(Case [] zone, Pion p) {
		for(Case c: zone) {
			if(p.position.equals(c)) {
				return true;
			}
		}
		return false;
	}
	//vérifie si un pion est dans la zone d'arrivée
	public boolean estDansLaZoneArr(Case[] arr,Pion p) {
		for(Case c: arr) {
			if(p.position.equals(c)) {
				return true;
			}
		}
		return false;
	}
	
}


package data;

import java.util.Arrays;

/**
 * Classe plateau
 */
public class Plateau {

	private Case[][] cases;
	private int lineCount;
	private int colomnCount;
	
	/**
	 * Constructeur de la classe plateau
	 * @param lineCount nombre de ligne
	 * @param colomneCount nombre de colonne
	 */
	public Plateau(int lineCount, int colomneCount) {
		this.lineCount = lineCount;
		this.colomnCount = colomneCount;
		cases = new Case[this.lineCount][this.colomnCount];
		//on parcours toutes les cases
		for (int i=0; i<this.lineCount; i++) {
            for (int j=0; j<this.colomnCount; j++) {
            	//on ne selectionne qu'une seule case sur deux
                if ((i%2==0 && j%2==0) || (i%2==1 && j%2==1)) {
                	//on ne  selectionne que les cases faisant partie d'un carré penché de 9 case de coté
                    if (Math.abs(i - this.lineCount/2) + Math.abs(j - this.colomnCount/2) <9 ) {
                    	cases [i][j] = new Case (i,j);
                    	//on assoscie a chaque case un certain score
                      	cases [i][j].setScore(i+Math.abs(j - this.colomnCount/2));
                      	//plus on descend dans le plateau plus le score est élevé 
                    	if (cases [i][j].getLigne()>12){
                    		cases [i][j].setScore(2*i*(i+Math.abs(j - this.colomnCount/2)));
                    	}
                    	if (cases [i][j].getLigne()>14){
                    		cases [i][j].setScore(3*i*(i+Math.abs(j - this.colomnCount/2)));
                    	}
                    	if (cases [i][j].getLigne()>16){
                    		cases [i][j].setScore(i*i*(i+Math.abs(j - this.colomnCount/2)));
                    	}
                    	//si la case est spéciale on y ajoute des points 
                    	if (cases [i][j].estCseSpe()){
                    		cases [i][j].setScore(i*(i+Math.abs(j - this.colomnCount/2)));
                    	}
                    }
                    else {
                    	cases [i][j] =new Case (-1,-1);
                    }
                }
                else {
                	cases [i][j] =new Case (-1,-1);
                }
            }

        }
	
	}

	public Case[][] getCases() {
		return cases;
	}

	public int getLineCount() {
		return lineCount;
	}
	public Case getCase(int line, int column) {
		return cases[line][column];
	}
	

	public int getColomnCount() {
		return colomnCount;
	}
	
	
	@Override
	public String toString() {
		return "plateau [map=" + Arrays.toString(cases) + ", lineCount=" + lineCount + ", colomnCount=" + colomnCount
				+ "]";
	}

}

